package dream.card.domain;


import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseTimeEntity;
import dream.common.exception.DuplicateException;
import dream.common.exception.NotFoundException;
import dream.common.exception.NotMatchException;
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
public class DreamCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dreamCardId;

    @JoinColumn(name = "dream_card_owner")
    @ManyToOne(fetch = FetchType.LAZY)
    private User dreamCardOwner;

    @JoinColumn(name = "dream_card_author")
    @ManyToOne(fetch = FetchType.LAZY)
    private User dreamCardAuthor;

    private String dreamCardContent;
    private String dreamCardImageUrl;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    private String dreamTelling;

    private int positivePoint;
    @Enumerated(EnumType.STRING)
    private Grade positiveGrade;

    private int rarePoint;
    @Enumerated(EnumType.STRING)
    private Grade rareGrade;

    @Enumerated(EnumType.STRING)
    private BaseCheckType auctionStatus;
    @Enumerated(EnumType.STRING)
    private BaseCheckType isShow;
    private long hits;

    @OneToMany(mappedBy = "dreamCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DreamCardLike> dreamCardLikes;

    @OneToMany(mappedBy = "cardId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardKeyword> cardKeyword;

    @OneToMany(mappedBy = "dreamCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WriggleReview> wriggleReviews;

    public void updateHits(){
        this.hits++;
    }


    public void addDreamCardLike(User user){

        boolean isDuplicate = dreamCardLikes.stream()
                .anyMatch(like -> like.getUser().getUserId().equals(user.getUserId()));

        if (isDuplicate) {
            throw new DuplicateException(DuplicateException.USER_LIKE_DUPLICATE);
        }
        dreamCardLikes.add(DreamCardLike.createLike(this, user));
    }

    public void deleteDreamCardLike(User user){

        List<DreamCardLike> likes = dreamCardLikes.stream()
                .filter(like -> like.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());

        if (likes.isEmpty()) throw new NotMatchException(NotMatchException.USER_LIKE_NOT_MATCH);

        dreamCardLikes.remove(likes.get(0));
    }


}
