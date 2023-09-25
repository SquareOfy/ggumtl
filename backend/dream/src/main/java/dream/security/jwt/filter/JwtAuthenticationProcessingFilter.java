package dream.security.jwt.filter;

import dream.common.domain.ResultTemplate;
import dream.common.exception.InvalidRefreshTokenException;
import dream.security.jwt.service.JwtService;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
//import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL1 = "/login/oauth2/code/kakao";
    private static final String NO_CHECK_URL2 = "/login";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override // 이 주소로 오는 건 토큰 없어도 됨.
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/oauth/login") || path.startsWith("/login/**") || path.startsWith("/oauth2/**");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //jwt를 검증할 필요가 없는 url은 다음 filter호출 후 메서드 종료하기
        if(request.getRequestURI().equals(NO_CHECK_URL1)||request.getRequestURI().equals(NO_CHECK_URL2)){
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> refreshToken = jwtService.extractRefreshToken(request);

        if(refreshToken.isPresent()){
            checkRefreshTokenAndReIssueNewToken(request, response, refreshToken.get());

        }else{
            checkAccessToken(request, response, filterChain);
        }

    }

    public void checkRefreshTokenAndReIssueNewToken(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        log.info("checkRefreshTokenAndReIsuueNewToken 동작");
        try {
            if (jwtService.isRefreshTokenValid(refreshToken)) {
                jwtService.removeRefreshToken(refreshToken);
                Long userId = jwtService.extractUserIdFromRefreshToken(refreshToken).get();

                log.info("RefreshToken & AccessToken 재발급");
                jwtService.sendTokenDto(response, jwtService.createTokenDto(userId));
            } else {
                throw new InvalidRefreshTokenException(InvalidRefreshTokenException.INVALID_REFRESH_TOKEN);
            }
        }catch (Exception e){

            log.error(e.getMessage());
        }

    }

    public void checkAccessToken(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
            log.info("checkAccessToken 호출");
            log.info("extract AccessToken : {}", jwtService.extractAccessToken(request));
            jwtService.extractAccessToken(request).filter(jwtService::isAccessTokenValid)
                    .ifPresent(accessToken -> jwtService.extractUserIdFromAccessToken(accessToken).
                            ifPresent(userId -> {
                                log.info("userId in checkAccessToken:{}", userId);
                                log.info("findUserId() : {}", userRepository.findByUserId(userId));
                                userRepository.findByUserId(userId)
                                        .ifPresent(this::saveAuthentication);


                            }));
            filterChain.doFilter(request, response);

    }


    public void saveAuthentication(User myUser) {
        log.info("myUser.getUserId(): {}", myUser.getUserId());
        log.info("myUser.getRole().name(): {}", myUser.getRole().name());
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getEmail())
                .password("null")
                .roles(myUser.getRole().name())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        log.info("authentification : {} ", authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Role in saveAuthentication : {}", authentication.getAuthorities().toString());

    }



}
