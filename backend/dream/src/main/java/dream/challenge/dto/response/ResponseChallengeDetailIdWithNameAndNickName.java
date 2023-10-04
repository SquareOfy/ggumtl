package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeDetail;
import dream.user.domain.Follow;
import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeDetailIdWithNameAndNickName {
    private long userId;
    private String imageUrl;
    private String nickname;

    public static ResponseChallengeDetailIdWithNameAndNickName from(Follow follow){

        ResponseChallengeDetailIdWithNameAndNickName response = new ResponseChallengeDetailIdWithNameAndNickName();
        response.userId = follow.getFromUser().getUserId();
        response.imageUrl = follow.getFromUser().getProfileUrl();
        response.nickname = follow.getFromUser().getNickname();

        return response;
    }
}
