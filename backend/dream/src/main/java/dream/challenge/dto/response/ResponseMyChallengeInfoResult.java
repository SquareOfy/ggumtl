package dream.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMyChallengeInfoResult {
    private List<ResponseMyChallengeInfo> challengeList;
    private boolean hasNext;

    public static ResponseMyChallengeInfoResult from(List<ResponseMyChallengeInfo> resultList, boolean hasNext) {

        ResponseMyChallengeInfoResult response = new ResponseMyChallengeInfoResult();

        response.challengeList = resultList;
        response.hasNext = hasNext;

        return response;
    }
}
