package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNightProfileHeaderByOther {

    Long userId;
    String nickname;
    String profileImageUrl;
    String profileImageName;
    double wrigglePoint;
//    int dreamCardCount;
    int followerCount;
    int followingCount;
    int dreamCardCount;


    public static ResponseNightProfileHeaderByOther from(User profileUser, int dreamCardCount, int followerCount, int followingCount){
        ResponseNightProfileHeaderByOther response = new ResponseNightProfileHeaderByOther();

        response.userId = profileUser.getUserId();
        response.nickname = profileUser.getNickname();
        response.profileImageName = profileUser.getProfileImageName();
        response.profileImageUrl = profileUser.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.wrigglePoint = profileUser.getWrigglePoint();
        response.dreamCardCount = dreamCardCount;


        return response;
    }
}
