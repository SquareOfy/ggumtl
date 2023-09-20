package dream.security.oauth2.userinfo;

import dream.user.domain.Role;
import dream.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Slf4j
@ToString
public class OAuth2Attributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    OAuth2UserInfo oAuth2UserInfo;

    @Builder
    public OAuth2Attributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo, Map<String, Object> attributes) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
        this.attributes = attributes;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName,
                                      Map<String, Object> attributes){
        if("kakao".equals(registrationId)){
            return ofKakao(userNameAttributeName, attributes);
        }
        return null;
    }


    public static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        log.info("ofKakao 호출");
        OAuth2Attributes oAuth2UserInfoOfKakao = OAuth2Attributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .attributes(attributes)
                .build();
        log.info("oAuth2UserInfoOfKakao : {} ", oAuth2UserInfoOfKakao.toString());
        return oAuth2UserInfoOfKakao;
    }

    public User toEntity(OAuth2UserInfo oAuth2UserInfo){
        log.info("UserEntity로 변환");
        log.info("oAuth2UserInfo : {} ", oAuth2UserInfo.toString());
        log.info("oAuth2UserInfo : {} ", oAuth2UserInfo.getEmail());

        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getNickname())
                .profileImageName("default.jpg")
                .profileUrl(oAuth2UserInfo.getImageUrl())
                .wrigglePoint(0.0)
                .point(500)
                .role(Role.GUEST)
                .build();
    }
}
