package dream.security.jwt.filter;

import dream.common.exception.InvalidRefreshTokenException;
import dream.security.jwt.service.JwtService;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.List;
import java.util.Optional;
//import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthentificationProcessingFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL = "/login/oauth2/code/kakao";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //jwt를 검증할 필요가 없는 url은 다음 filter호출 후 메서드 종료하기
        if(request.getRequestURI().equals(NO_CHECK_URL)){
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> refreshToken = jwtService.extractRefreshToken(request);

        if(refreshToken.isPresent()){
            checkRefreshTokenAndReIssueNewToken(response, refreshToken.get());

        }else{
            checkAccessToken(request, response, filterChain);
        }

    }

    public void checkRefreshTokenAndReIssueNewToken(HttpServletResponse response, String refreshToken) {
        if(jwtService.isRefreshTokenValid(refreshToken)){
            Optional<Long> userIdOptional = jwtService.extractUserIdFromRefreshToken(refreshToken);
            jwtService.removeRefreshToken(refreshToken);

            if(userIdOptional.isPresent()) {
                log.info("RefreshToken & AccessToken 재발급");
                jwtService.sendTokenDto(response, jwtService.createTokenDto(userIdOptional.get()));

            }

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
        log.info("saveAuthentication : {}"+myUser.toString());
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
        log.info("getAuthorities() : {}",authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));
        log.info("authentification : {} ", authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Role in saveAuthentication : {}", authentication.getAuthorities().toString());

    }



}
