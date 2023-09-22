package dream.profile.dto.response;

import dream.challenge.domain.Badge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileBadgeDetail {

    private String period;
    private String content;
    private String keyword;


    public static ResponseProfileBadgeDetail from(Badge badge){

        ResponseProfileBadgeDetail response = new ResponseProfileBadgeDetail();
        response.period = badge.getChallenge().getPeriod();
        response.content = badge.getChallenge().getChallengeContent();
        response.keyword = badge.getChallenge().getKeywords().get(0).getKeyword().getKeyword();

        return response;

    }
}
