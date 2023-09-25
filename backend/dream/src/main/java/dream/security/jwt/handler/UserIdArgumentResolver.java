package dream.security.jwt.handler;

import dream.common.exception.InvalidAccessTokenException;
import dream.common.exception.NoSuchElementException;
import dream.security.jwt.domain.UserInfo;
import dream.security.jwt.service.JwtService;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();


        String accessToken = jwtService.extractAccessToken(request).get();

        log.info("annotation Test");

        log.info("token in resolveArgument : {}", accessToken);
        Long expiredAt = jwtService.getExpiration(accessToken);
        Optional<User> user = userRepository.findByUserId(jwtService.extractUserIdFromAccessToken(accessToken).get());
        if(user.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USER);
        return user.get();

    }
}
