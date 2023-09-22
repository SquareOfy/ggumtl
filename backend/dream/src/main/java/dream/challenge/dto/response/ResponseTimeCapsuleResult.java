package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTimeCapsuleResult {
    private Long challengeId;
    private String challengeTitle;
    private List<ResponseTimeCapsule> timeCapsuleList;
    private boolean hasNext;


    public static ResponseTimeCapsuleResult from(Challenge challenge, ArrayList<ResponseTimeCapsule> timeCapsules, boolean hasNext) {

        ResponseTimeCapsuleResult response = new ResponseTimeCapsuleResult();

        response.challengeId = challenge.getChallengeId();
        response.challengeTitle = challenge.getChallengeTitle();
        response.timeCapsuleList = timeCapsules;
        response.hasNext = hasNext;

        return response;
    }
}
