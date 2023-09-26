package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeDetail;
import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeDetail {
    private Long challengeDetailId;
    private Long userId;
    private String nickname;
    private String challengeDetailContent;
    private int likeCount;
    private String photoUrl;
    private boolean isLike;
    private int commentCount;
    private int challengeDetailCount;
    private LocalDateTime createdAt;

    public static ResponseChallengeDetail from(ChallengeDetail challengeDetail, boolean isLike, int count) {

        ResponseChallengeDetail response = new ResponseChallengeDetail();

        response.challengeDetailId = challengeDetail.getChallengeDetailId();
        response.userId = challengeDetail.getUser().getUserId();
        response.nickname = challengeDetail.getUser().getNickname();
        response.challengeDetailContent = challengeDetail.getChallengeDetailContent();
        response.likeCount = challengeDetail.getChallengeDetailLikes().size();
        response.photoUrl = challengeDetail.getPhotoUrl();
        response.commentCount = challengeDetail.getComments().size();
        response.isLike = isLike;
        response.createdAt = challengeDetail.getCreatedAt();
        response.challengeDetailCount = count;

        return response;
    }
}