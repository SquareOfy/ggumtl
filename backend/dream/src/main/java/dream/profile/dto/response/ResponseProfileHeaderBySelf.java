package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileHeaderBySelf {

    private Long userId;
    private String nickname;
    private String profileImageUrl;
    private String profileImageName;
    private int point;
    private Double wrigglePoint;
    private int finishChallengeCount;
    private int followerCount;
    private int followingCount;
    private int dreamCardCount;


    public static ResponseProfileHeaderBySelf from(User user, int finishChallengeCount, int followerCount, int followingCount, int dreamCardCount) {
        ResponseProfileHeaderBySelf response = new ResponseProfileHeaderBySelf();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImageName = user.getProfileImageName();
        response.profileImageUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.finishChallengeCount = finishChallengeCount;
        response.point = user.getPoint();
        response.wrigglePoint = user.getWrigglePoint();

        response.dreamCardCount = dreamCardCount;

        return response;
    }
}
