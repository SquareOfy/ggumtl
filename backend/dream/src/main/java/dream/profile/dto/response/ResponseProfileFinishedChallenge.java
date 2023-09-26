package dream.profile.dto.response;

import dream.challenge.domain.Challenge;
import dream.challenge.domain.ChallengeParticipation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileFinishedChallenge {

    private Long challengeParticipateId;
    private Long challlengeId;
    private String challengeTitle;
    private String period;

    private int participationCount;


    public static ResponseProfileFinishedChallenge from(ChallengeParticipation challengeParticipation){
        ResponseProfileFinishedChallenge response = new ResponseProfileFinishedChallenge();
        response.challengeParticipateId = challengeParticipation.getChallengeParticipationId();
        response.challlengeId = challengeParticipation.getChallenge().getChallengeId();
        response.challengeTitle = challengeParticipation.getChallenge().getChallengeTitle();
        response.period = challengeParticipation.getChallenge().getPeriod();
        response.participationCount = challengeParticipation.getChallenge().getChallengeParticipations().size();
        return response;

    }
}
