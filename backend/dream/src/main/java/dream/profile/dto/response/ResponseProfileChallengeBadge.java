package dream.profile.dto.response;

import dream.challenge.domain.Badge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileChallengeBadge {

    private Long badgeId;
    private Long challengeId;
    private String completedDays;
    private String badgeUrl;

    public static ResponseProfileChallengeBadge from(Badge badge){
        ResponseProfileChallengeBadge response = new ResponseProfileChallengeBadge();

        response.badgeId = badge.getBadgeId();
        response.challengeId = badge.getChallenge().getChallengeId();
        response.completedDays = badge.getCompleteDays()+"일";
        response.badgeUrl = badge.getChallenge().getBadgeUrl();

        return response;
    }
}
