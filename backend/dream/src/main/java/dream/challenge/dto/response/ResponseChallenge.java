package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import dream.common.exception.DataException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallenge {

    private Long challengeId;
    private String title;
    private String period;
    private int participateCount;
    private Long dreamKeywordId;
    private String badgeUrl;



    public static ResponseChallenge from(Challenge challenge){

        ResponseChallenge response = new ResponseChallenge();

        if(challenge.getKeywords().isEmpty() ||challenge.getKeywords().size()==0)
            throw new DataException(DataException.NO_KEYWORDID_FOUND, challenge.getChallengeId());
        response.challengeId = challenge.getChallengeId();
        response.title = challenge.getChallengeTitle();
        response.period = challenge.getPeriod();
        response.participateCount = challenge.getChallengeParticipations().size();
        response.dreamKeywordId = challenge.getKeywords().get(0).getKeyword().getKeywordId();
        response.badgeUrl = challenge.getBadgeUrl();
        return response;
    }

}
