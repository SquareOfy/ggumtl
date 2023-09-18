package dream.security.oauth2.handler;

import dream.common.domain.ResultTemplate;
import dream.security.jwt.dto.TokenDto;
import dream.security.jwt.service.JwtService;
import dream.security.oauth2.userinfo.CustomOAuth2User;
import dream.user.controller.UserController;
import dream.user.domain.Role;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserController userController;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Social Login 성공");


        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        if(oAuth2User.getRole().equals(Role.GUEST)){
            TokenDto tokenDto = jwtService.createTokenDto(oAuth2User.getUserId());
            log.info("userId :{}" ,oAuth2User.getUserId());
            log.info("oAuth2User : {}", oAuth2User);
            String accessToken = tokenDto.getAccessToken();
            response.addHeader(jwtService.getAccessHeader(),  "Bearer " + accessToken);
            log.info("response : {}", response.getHeader(jwtService.getAccessHeader()));
//            log.info("userId : {} ",jwtService.extractUserIdFromAccessToken(accessToken));
            jwtService.sendTokenDto(response, tokenDto);
//            response.sendRedirect("http://localhost:3000/sunset/signup");


        }else{
            loginSuccess(response, oAuth2User);
        }

    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        TokenDto tokenDto = jwtService.createTokenDto(oAuth2User.getUserId());
        String accessToken = tokenDto.getAccessToken();
        String refreshToken = tokenDto.getRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendTokenDto(response, tokenDto);
    }
}
