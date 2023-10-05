package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileHeaderByOther {

   private Long userId;
    private String nickname;
    private String profileImageUrl;
    private String profileImageName;
    private double wrigglePoint;
    private int finishChallengeCount;
    private int followerCount;
    private int followingCount;
    private int dreamCardCount;


    public static ResponseProfileHeaderByOther from(User user, int finishChallengeCount, int followerCount, int followingCount, int dreamCardCount){
        ResponseProfileHeaderByOther response = new ResponseProfileHeaderByOther();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImageName = user.getProfileImageName();
        response.profileImageUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.wrigglePoint = user.getWrigglePoint();
        response.finishChallengeCount = finishChallengeCount;
        response.dreamCardCount = dreamCardCount;

        return response;
    }
}
