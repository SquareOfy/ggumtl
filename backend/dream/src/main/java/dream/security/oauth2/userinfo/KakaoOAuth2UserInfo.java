package dream.security.oauth2.userinfo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    protected Map<String, Object> attributes;

    public KakaoOAuth2UserInfo(Map<String , Object> attributes) {
        super(attributes);
        this.attributes = attributes;
    }

    @Override
    public String getNickname() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }

    @Override
    public String getEmail() {

        log.info("getEmail() 실행");
        log.info("attributes : {} ", attributes);

        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        log.info("account : {}", account.toString());
//        Map<String, Object> profile = (Map<String, Object>) account.get("kakao_account");

        if (account == null ) {
            return null;
        }

        return (String) account.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");
    }


}

