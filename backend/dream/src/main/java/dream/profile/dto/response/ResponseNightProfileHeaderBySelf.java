package dream.profile.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNightProfileHeaderBySelf {


    private Long userId;
    private String nickname;
    private String profileImageUrl;
    private String profileImageName;
    private int point;
    private Double wrigglePoint;
    private int dreamCardCount;
    private int followerCount;
    private int followingCount;


    public static ResponseNightProfileHeaderBySelf from(User user, int dreamCardCount, int followerCount, int followingCount) {
        ResponseNightProfileHeaderBySelf response = new ResponseNightProfileHeaderBySelf();

        response.userId = user.getUserId();
        response.nickname = user.getNickname();
        response.profileImageName = user.getProfileImageName();
        response.profileImageUrl = user.getProfileUrl();
        response.followerCount = followerCount;
        response.followingCount = followingCount;
        response.dreamCardCount = dreamCardCount;
        response.point = user.getPoint();
        response.wrigglePoint = user.getWrigglePoint();


        return response;
    }


}
