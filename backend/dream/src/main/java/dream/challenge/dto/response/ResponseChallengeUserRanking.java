package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeDetail;
import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeUserRanking {
    private int rank;
    private String nickName;

    public static ResponseChallengeUserRanking from(User user, int rank){

        ResponseChallengeUserRanking response = new ResponseChallengeUserRanking();

        response.setRank(rank);
        response.setNickName(user.getNickname());

        return response;
    }
}
