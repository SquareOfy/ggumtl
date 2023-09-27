package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMyChallengeInfo {
    private Long challengeId;
    private String challengeTitle;
    private String period;
    private int participationCount;
    private String badgeUrl;

    public static ResponseMyChallengeInfo from(Challenge challenge) {

        ResponseMyChallengeInfo response = new ResponseMyChallengeInfo();

        response.challengeId = challenge.getChallengeId();
        response.challengeTitle = challenge.getChallengeTitle();
        response.period = challenge.getPeriod();
        response.participationCount = challenge.getChallengeParticipations().size();
        response.badgeUrl = challenge.getBadgeUrl();
        return response;
    }
}
