package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileHeaderBySelf {

    Long userId;
    String nickname;
    String profileImgUrl;
    String profileImgName;
    int point;
    Double wrigglePoint;
    int followerCount;
    int followingCount;




    public static ResponseProfileHeaderBySelf from(User user, int followerCount, int followingCount){
        ResponseProfileHeaderBySelf response = new ResponseProfileHeaderBySelf();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImgName = user.getProfileImageName();
        response.profileImgUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;

        response.point = user.getPoint();
        response.wrigglePoint = user.getWrigglePoint();


        return response;
    }
}
