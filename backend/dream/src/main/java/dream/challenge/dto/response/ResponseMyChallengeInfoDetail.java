package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMyChallengeInfoDetail {
    private Long challengeId;
    private String challengeTitle;
    private String period;
    private String challengeContent;
    private int challengeDetailCount;
    private boolean isChallengeUpload;

    public static ResponseMyChallengeInfoDetail from(Challenge challenge, boolean canWrite) {

        ResponseMyChallengeInfoDetail response = new ResponseMyChallengeInfoDetail();

        response.challengeId = challenge.getChallengeId();
        response.challengeTitle = challenge.getChallengeTitle();
        response.challengeContent = challenge.getChallengeContent();
        response.period = challenge.getPeriod();
        response.challengeDetailCount = challenge.getChallengeParticipations().size();
        response.isChallengeUpload = canWrite;

        return response;
    }
}
