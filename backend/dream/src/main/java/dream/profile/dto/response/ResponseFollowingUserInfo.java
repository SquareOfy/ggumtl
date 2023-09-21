package dream.profile.dto.response;

import dream.user.domain.Follow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFollowingUserInfo {
    private Long followId;
    private Long userId;
    private String profileImgName;
    private String profileImgUrl;
    private String nickname;

    public static ResponseFollowingUserInfo from(Follow follow){
        ResponseFollowingUserInfo response = new ResponseFollowingUserInfo();

        response.followId = follow.getFollowId();
        response.userId = follow.getToUser().getUserId();
        response.nickname=follow.getToUser().getNickname();
        response.profileImgName=follow.getToUser().getProfileImageName();
        response.profileImgUrl=follow.getToUser().getProfileUrl();

        return response;
    }



}
