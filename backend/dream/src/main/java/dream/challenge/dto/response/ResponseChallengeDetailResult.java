package dream.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeDetailResult {
    private List<ResponseChallengeDetail> resultList;
    private boolean hasNext;

    public static ResponseChallengeDetailResult from(List<ResponseChallengeDetail> challengeDetailList, boolean hasNext) {

        ResponseChallengeDetailResult response = new ResponseChallengeDetailResult();

        response.resultList = challengeDetailList;
        response.hasNext = hasNext;

        return response;
    }
}