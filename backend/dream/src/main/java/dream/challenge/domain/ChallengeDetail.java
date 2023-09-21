package dream.challenge.domain;


import dream.card.domain.CardKeyword;
import dream.card.domain.DreamCard;
import dream.card.domain.DreamKeyword;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseUpdateTimeEntity;
import dream.s3.dto.request.RequestChallengeDetail;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeDetail extends BaseUpdateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeDetailId;

    @JoinColumn(name = "challenge_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String photoUrl;
    private String challengeDetailTitle;
    private String challengeDetailContent;

    private long hits;
    private long maxDays;

    public static ChallengeDetail makeChallengeDetail(User user,RequestChallengeDetail request, Challenge challenge, String fileName){

        ChallengeDetail challengeDetail = new ChallengeDetail();
        challengeDetail.challenge = challenge;
        challengeDetail.challengeDetailTitle = request.getChallengeDetailTitle();
        challengeDetail.challengeDetailContent = request.getChallengeDetailContent();
        challengeDetail.photoUrl = fileName;
        challengeDetail.user = user;

        return challengeDetail;
    }
}
