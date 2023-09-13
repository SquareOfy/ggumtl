package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSearchedChallenge {

    private String title;
    private String period;
    private long challengeId;
    private int participantCount;

    public static ResponseSearchedChallenge from(Challenge challenge){
        ResponseSearchedChallenge response = new ResponseSearchedChallenge();
        response.title = challenge.getChallengeTitle();
        response.period = challenge.getPeriod();
        response.challengeId = challenge.getChallengeId();
        response.participantCount = challenge.getChallengeParticipations().size();
        return response;
    }
}
