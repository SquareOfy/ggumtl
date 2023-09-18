package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeList {

    private List<ResponseChallenge> challengeList;
    private boolean hasNext;

    public static ResponseChallengeList from(List<ResponseChallenge> challengeList, boolean hasNext){

        ResponseChallengeList response = new ResponseChallengeList();
        response.challengeList = challengeList;
        response.hasNext = hasNext;

        return response;
    }
}
