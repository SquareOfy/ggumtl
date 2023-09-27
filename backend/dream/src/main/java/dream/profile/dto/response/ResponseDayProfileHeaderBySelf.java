package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDayProfileHeaderBySelf {

    Long userId;
    String nickname;
    String profileImageUrl;
    String profileImageName;
    int point;
    Double wrigglePoint;
    int finishChallengeCount;
    int followerCount;
    int followingCount;




    public static ResponseDayProfileHeaderBySelf from(User user, int finishChallengeCount, int followerCount, int followingCount){
        ResponseDayProfileHeaderBySelf response = new ResponseDayProfileHeaderBySelf();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImageName = user.getProfileImageName();
        response.profileImageUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.finishChallengeCount = finishChallengeCount;
        response.point = user.getPoint();
        response.wrigglePoint = user.getWrigglePoint();


        return response;
    }
}
