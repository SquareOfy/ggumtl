package dream.security.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dream.security.jwt.dto.RefreshTokenDto;
import dream.security.jwt.dto.TokenDto;
import dream.security.jwt.repository.TokenRepository;
import dream.user.domain.Role;
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


    private final RedisTemplate redisTemplate;
    private final TokenRepository tokenRepository;

    /**
     * JWT TokenDto 생성
     **/

    public TokenDto createTokenDto(Long userId) {

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
                .sign(Algorithm.HMAC512(secretKey));


        //redis에 refreshToken 객체 생성하여 저장
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder().
                userId(userId).
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
        tokenRepository.findByRefreshToken(refreshToken)
                .ifPresent(refreshTokenDto -> {
                    tokenRepository.deleteByRefreshToken(refreshToken);
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

            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build().verify(accessToken.replace(BEARER, ""))
                    .getClaim(USER_ID_CLAIM).asLong());

            //어떻게 처리해야할지 모르겠다 ,,
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. in extractUserIdFromAccessToken {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Long> extractUserIdFromRefreshToken(String refreshToken) {
        Optional<RefreshTokenDto> refreshTokenDtoOptional = tokenRepository.findByRefreshToken(refreshToken);

        return refreshTokenDtoOptional.map(RefreshTokenDto::getUserId);
    }


    /**
     * 헤더에서 RefreshToken 추출
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        log.info("extractRefreshToken 동작");
        log.info("추출한 Refresh Token : {}", Optional.ofNullable(request.getHeader(refreshHeader)).get());
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    /**
     * 헤더에서 AccessToken 추출
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        log.info("extract AccessToken");
        log.info("accessHeader Token : {} ",request.getHeader(accessHeader));
        return Optional.ofNullable(request.getHeader(accessHeader))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    /**
     * Access Token 유효성 검증
     */
    public boolean isAccessTokenValid(String accessToken) {
        try {
            log.info("isAccessTokenValid 동작");
                    
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken);
            return true;
            //이부분 Exception으로 만들어야 하는가...?
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. in isAccessTokenValid {}", e.getMessage());
            return false;
        }
    }
    /**
     * Refresh Token 유효성 검증
     */
    public boolean isRefreshTokenValid(String refreshToken) {
        log.info("isRefreshTokenValid 동작");

        Optional<RefreshTokenDto> savedRefreshTokenDto = tokenRepository.findByRefreshToken(String.valueOf(refreshToken));

        if (!savedRefreshTokenDto.isPresent()) {
            log.error("유효하지 않은 리프레시 토큰입니다.");
            return false;
        }

        // 여기서 JWT 라이브러리를 사용해 리프레시 토큰의 서명을 검증할 수 있습니다.
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(refreshToken);
        } catch (Exception e) {
            log.error("유효하지 않은 리프레시 토큰입니다. {}", e.getMessage());
            return false;
        }

        return true;
    }


}








