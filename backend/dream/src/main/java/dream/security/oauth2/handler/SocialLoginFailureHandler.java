package dream.security.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class SocialLoginFailureHandler{
//    implements
//} AuthenticationFailureHandler {

        // 실패 요인 추적을 위한 로그 추가
        log.error("Request URL: {}", request.getRequestURL());
        log.error("Query Parameters: {}", request.getQueryString());
        log.error("code Parameter : {}",request.getParameter("code"));
        log.error("Client IP: {}", request.getRemoteAddr());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.debug("Header: {}: {}", headerName, request.getHeader(headerName));
        }

        if (exception instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
            if ("authorization_request_not_found".equals(error.getErrorCode())) {

                log.error("OAuth2 Error Code: {}", error.getErrorCode());
                log.error("OAuth2 Error Description: {}", error.getDescription());
            }
        }

        // 에러 스택 트레이스
        log.error("Exception Stack Trace: ", exception);

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("소셜 로그인 실패! 서버 로그를 확인해주세요.");
        log.info("소셜 로그인에 실패했습니다. 에러 메시지 : {}", exception.getMessage());

    }


