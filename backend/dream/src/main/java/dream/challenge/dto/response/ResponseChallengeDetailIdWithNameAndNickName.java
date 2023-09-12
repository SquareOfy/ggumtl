package dream.challenge.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeDetailIdWithNameAndNickName {
    private long challengeDetailId;
    private long userId;
    private String nickname;

    public static ResponseChallengeDetailIdWithNameAndNickName from(User user){

        ResponseChallengeDetailIdWithNameAndNickName response = new ResponseChallengeDetailIdWithNameAndNickName();
        response.userId = user.getUserId();
        response.nickname = user.getNickname();

        return response;
    }
}
