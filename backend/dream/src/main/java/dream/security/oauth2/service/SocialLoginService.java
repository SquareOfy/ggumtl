package dream.security.oauth2.service;

import dream.security.oauth2.userinfo.CustomOAuth2User;
import dream.security.oauth2.userinfo.OAuth2Attributes;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialLoginService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private static final String KAKAO = "kakao";
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("SocialLoginService.loadUser() 실행 - 로그인 요청 진입");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)
        OAuth2Attributes extractAttributes = OAuth2Attributes.of(registrationId,userNameAttributeName, attributes);
        log.info("registrationId : {}", registrationId);
        log.info("userNameAttributeName : {}", userNameAttributeName);
        log.info("attributes : {}", attributes);
        log.info("extractAttributes : {}", extractAttributes.toString());
        User createdUser = getUser(extractAttributes);

        return new CustomOAuth2User(Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())), attributes, extractAttributes.getNameAttributeKey(), createdUser.getRole(), createdUser.getUserId());
    }


    private User getUser(OAuth2Attributes attributes) {
        log.info("getUser 동작");
        log.info("getOauthUserInfo() : {} ",attributes.getOAuth2UserInfo().toString());
//        log.info("email : {} ", attributes.getOAuth2UserInfo().getEmail());
        Optional<User> findUser = userRepository.findByEmail(attributes.getOAuth2UserInfo().getEmail());

        if(findUser.isPresent()) {

            return findUser.get();
        }
        else return saveUser(attributes);
    }

    private User saveUser(OAuth2Attributes attributes) {
    log.info("회원가입을 위한 GUEST 저장");
    User createUser = attributes.toEntity(attributes.getOAuth2UserInfo());
    return userRepository.save(createUser);
    }
}
