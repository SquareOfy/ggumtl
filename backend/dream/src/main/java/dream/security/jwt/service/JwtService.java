package dream.security.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dream.common.exception.InvalidAccessTokenException;
import dream.common.exception.InvalidRefreshTokenException;
import dream.common.exception.NoSuchElementException;
import dream.security.jwt.dto.RefreshTokenDto;
import dream.security.jwt.dto.TokenDto;
import dream.security.jwt.repository.TokenRepository;
import dream.user.domain.Role;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;


    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USER_ID_CLAIM = "userId";
    private static final String BEARER = "Bearer ";
    private static final String USER_EMAIL_CLAIM = "email";

    private final RedisTemplate redisTemplate;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    /**
     * JWT TokenDto 생성
     **/

    public TokenDto createTokenDto(Long userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USER);

        Date now = new Date();

        String accessToken = JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
                .withClaim(USER_ID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey));

        String refreshToken = JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .withClaim(USER_ID_CLAIM, user.get().getUserId())
                .withClaim(USER_EMAIL_CLAIM, user.get().getEmail())
                .sign(Algorithm.HMAC512(secretKey));


        //redis에 refreshToken 객체 생성하여 저장
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder().
                email(user.get().getEmail()).
                refreshToken(refreshToken).build();

        tokenRepository.saveRefreshToken(refreshTokenDto);

        log.info("Access Token 발급 완료 : {}", accessToken);
        log.info("Refresh Token 발급 완료 : {}", refreshToken);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();


    }

    /**
     * refreshToken을 redis에서 삭제
     */
    public void removeRefreshToken(String refreshToken) {

        Optional<String> email = extractEmailFromRefreshToken(refreshToken);
        if (email.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EMAIL_IN_REFRESH_TOKEN);
        tokenRepository.findByEmail(email.get())

                .ifPresent(refreshTokenDto -> {
                    tokenRepository.deleteByEmail(email.get());
                    log.info("Refresh Token 삭제 : {} ", refreshToken);


                });
    }

    /**
     * Token 헤더에 넣어 보내기
     */

    public void sendTokenDto(HttpServletResponse response, TokenDto tokenDto) {

        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, BEARER+tokenDto.getAccessToken());
        response.setHeader(refreshHeader, BEARER+tokenDto.getRefreshToken());

        log.info("Access Token, Refresh Token 헤더 설정 완료");

    }
    /**
    * 토큰에서 userId 정보 꺼내기
     */

    public Optional<Long> extractUserIdFromAccessToken(String accessToken) {
        log.info("token in extractUserIdFromAccessToken : {}", accessToken);
        try {
            Optional<Long> userId = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build().verify(accessToken.replace(BEARER, ""))
                    .getClaim(USER_ID_CLAIM).asLong());
            if (userId.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USERID_IN_ACCESS_TOKEN);

            return userId;
        } catch (Exception e) {
            throw new InvalidAccessTokenException(InvalidAccessTokenException.INVALID_ACCESS_TOKEN);
        }
    }
    public Optional<String> extractEmailFromRefreshToken(String refreshToken) {

        Optional<String> email = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(refreshToken.replace(BEARER, ""))
                .getClaim(USER_EMAIL_CLAIM).asString());

        if (email.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EMAIL_IN_REFRESH_TOKEN);


        return email;
    }


    public Optional<Long> extractUserIdFromRefreshToken(String refreshToken) {
        Optional<Long> userId = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(refreshToken.replace(BEARER, ""))
                .getClaim(USER_ID_CLAIM).asLong());

        if (userId.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USERID_IN_REFRESH_TOKEN);


        return userId;
    }




    /**
     * 헤더에서 RefreshToken 추출
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        log.info("extractRefreshToken 동작");
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    /**
     * 헤더에서 AccessToken 추출
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        log.info("extract AccessToken");
        log.info("accessHeader Token in Header: {} ",request.getHeader(accessHeader));
        Optional<String> accessToken = Optional.ofNullable(request.getHeader(accessHeader))
                .map(token -> token.replace(BEARER, ""));
        if (accessToken.isEmpty())
            throw new NoSuchElementException(dream.common.exception.NoSuchElementException.NO_SUCH_ACCESSTOKEN_IN_HEADER);
        return accessToken;
    }
    public Long getExpiration(String accessToken) {
        Optional<Date> expiration = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(accessToken.replace(BEARER, ""))
                .getExpiresAt());
        if (expiration.isEmpty())
            throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EXPIREAT_IN_ACCESS_TOKEN);
        Long now = new Date().getTime();
        return expiration.get().getTime() - now;

    }


    /**
     * Access Token 유효성 검증
     */
    public boolean isAccessTokenValid(String accessToken) {
        log.info("isAccessTokenValid 동작");

        //REDIS의 블랙리스트에 해당 AccessToken이 존재하는지 확인
        Optional<String> isLogoutOption = tokenRepository.findByKey(accessToken);
        //있다면 유효하지 않은 accessToken 예외 반환
        if (isLogoutOption.isPresent())
            throw new InvalidAccessTokenException(InvalidAccessTokenException.INVALID_ACCESS_TOKEN);

        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken);
            return true;
        } catch (Exception e) {
            throw new InvalidAccessTokenException(InvalidAccessTokenException.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Refresh Token 유효성 검증
     */
    public boolean isRefreshTokenValid(String refreshToken) {
        log.info("isRefreshTokenValid 동작");

        Optional<String> email = extractEmailFromRefreshToken(refreshToken);
        log.info("refresh에서 추출한 email : {} " , email.get());
        if (email.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EMAIL_IN_REFRESH_TOKEN);
        Optional<RefreshTokenDto> savedRefreshTokenDto = tokenRepository.findByEmail(email.get());


        if (!savedRefreshTokenDto.isPresent()) {
            throw new InvalidRefreshTokenException(InvalidRefreshTokenException.INVALID_REFRESH_TOKEN);
        }

        // 여기서 JWT 라이브러리를 사용해 리프레시 토큰의 서명을 검증할 수 있습니다.
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(savedRefreshTokenDto.get().getRefreshToken());
        } catch (Exception e) {
            throw new InvalidRefreshTokenException(InvalidRefreshTokenException.INVALID_REFRESH_TOKEN);
        }

        return true;
    }
    public void saveBlackList(String accessToken) {


        if (isAccessTokenValid(accessToken)) {

            tokenRepository.saveBlackList(accessToken, getExpiration(accessToken));

        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }

}








