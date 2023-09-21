package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeInfoDetail {
    private long challengeId;
    private String challengeTitle;
    private String challengeContent;
    private String period;
    private String badgeUrl;
    private int participationCount;
    private String keyword;
    private List<ResponseChallengeUserRanking> ranking;

    public static ResponseChallengeInfoDetail from(Challenge challengeWithKeyword, Challenge challengeWithParticipates,
                                                   List<ResponseChallengeUserRanking> ranking){

        ResponseChallengeInfoDetail response = new ResponseChallengeInfoDetail();

        response.challengeId = challengeWithKeyword.getChallengeId();
        response.challengeTitle = challengeWithKeyword.getChallengeTitle();
        response.challengeContent = challengeWithKeyword.getChallengeContent();
        response.period = challengeWithKeyword.getPeriod();
        response.badgeUrl = challengeWithKeyword.getBadgeUrl();
        response.participationCount = challengeWithParticipates.getChallengeParticipations().size();
        response.keyword = challengeWithKeyword.getKeywords().get(0).getKeyword().getKeyword();
        response.ranking = ranking;

        return response;
    }
}
