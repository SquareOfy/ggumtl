package dream.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileFinishedChallengeList {

    List<ResponseProfileFinishedChallenge> challengeList;
    boolean hasNext;

    public static ResponseProfileFinishedChallengeList from(List<ResponseProfileFinishedChallenge> list, boolean hasNext){
        ResponseProfileFinishedChallengeList response = new ResponseProfileFinishedChallengeList();

        response.challengeList = list;
        response.hasNext = hasNext;

        return response;
    }

}
