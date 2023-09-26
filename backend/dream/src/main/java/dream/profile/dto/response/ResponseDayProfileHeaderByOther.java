package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDayProfileHeaderByOther {

    Long userId;
    String nickname;
    String profileImageUrl;
    String profileImageName;
    double wrigglePoint;
    int finishChallengeCount;
    int followerCount;
    int followingCount;


    public static ResponseDayProfileHeaderByOther from(User user, int finishChallengeCount, int followerCount, int followingCount){
        ResponseDayProfileHeaderByOther response = new ResponseDayProfileHeaderByOther();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImageName = user.getProfileImageName();
        response.profileImageUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.wrigglePoint = user.getWrigglePoint();
        response.finishChallengeCount = finishChallengeCount;


        return response;
    }
}
