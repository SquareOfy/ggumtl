package dream.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSearchedChallengeList {

    private List<ResponseSearchedChallenge> challengeList;
    private boolean hasNext;

    public static ResponseSearchedChallengeList from(List<ResponseSearchedChallenge> list, boolean hasNext) {

        ResponseSearchedChallengeList response = new ResponseSearchedChallengeList();

        response.challengeList = list;
        response.hasNext = hasNext;

        return response;
    }
}
