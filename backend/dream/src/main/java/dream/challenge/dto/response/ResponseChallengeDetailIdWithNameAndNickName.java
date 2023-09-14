package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeDetail;
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

    public static ResponseChallengeDetailIdWithNameAndNickName from(ChallengeDetail challengeDetail){

        ResponseChallengeDetailIdWithNameAndNickName response = new ResponseChallengeDetailIdWithNameAndNickName();
        response.challengeDetailId = challengeDetail.getChallengeDetailId();
        response.userId = challengeDetail.getUser().getUserId();
        response.nickname = challengeDetail.getUser().getNickname();

        return response;
    }
}
