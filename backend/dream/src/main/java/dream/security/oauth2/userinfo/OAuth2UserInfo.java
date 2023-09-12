package dream.security.oauth2.userinfo;

import lombok.ToString;

import java.util.Map;

@ToString
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    public abstract String getNickname();

    public abstract String getEmail();

    public abstract String getImageUrl();

}
