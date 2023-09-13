package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFollowingUserStory {
    private long challengeId;
    private String challengeTitle;
    private long challengeDetailId;
    private String photoUrl;
    private String challengeDetailContent;

    public static ResponseFollowingUserStory from(ChallengeDetail challengeDetail){

        ResponseFollowingUserStory response = new ResponseFollowingUserStory();
        response.challengeId = challengeDetail.getChallenge().getChallengeId();
        response.challengeTitle = challengeDetail.getChallenge().getChallengeTitle();
        response.challengeDetailId = challengeDetail.getChallengeDetailId();
        response.photoUrl = challengeDetail.getPhotoUrl();
        response.challengeDetailContent = challengeDetail.getChallengeDetailContent();

        return response;
    }
}
