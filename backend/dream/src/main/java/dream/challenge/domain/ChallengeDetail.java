package dream.challenge.domain;


import dream.card.domain.CardKeyword;
import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.card.domain.DreamKeyword;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseUpdateTimeEntity;
import dream.common.exception.DuplicateException;
import dream.common.exception.NotMatchException;
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

    @OneToMany(mappedBy = "challengeDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeDetailLike> challengeDetailLikes;

    public static ChallengeDetail makeChallengeDetail(User user,RequestChallengeDetail request, Challenge challenge, String fileName){

        ChallengeDetail challengeDetail = new ChallengeDetail();
        challengeDetail.challenge = challenge;
        challengeDetail.challengeDetailTitle = request.getChallengeDetailTitle();
        challengeDetail.challengeDetailContent = request.getChallengeDetailContent();
        challengeDetail.photoUrl = fileName;
        challengeDetail.user = user;

        return challengeDetail;
    }

    public void addChallengeDetailLike(User user){

        boolean isDuplicate = challengeDetailLikes.stream()
                .anyMatch(like -> like.getUser().getUserId().equals(user.getUserId()));

        if (isDuplicate) {
            throw new DuplicateException(DuplicateException.CHALLENGE_DETAIL_LIKE_DUPLICATE);
        }
        challengeDetailLikes.add(ChallengeDetailLike.createLike(this, user));
    }

    public void deleteChallengeDetailLike(User user){

        List<ChallengeDetailLike> likes = challengeDetailLikes.stream()
                .filter(like -> like.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());

        if (likes.isEmpty()) throw new NotMatchException(NotMatchException.USER_DETAIL_LIKE_NOT_MATCH);

        challengeDetailLikes.remove(likes.get(0));
    }
}
