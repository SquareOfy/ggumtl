package dream.card.domain;


import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Grade grade;

    private String dreamTelling;
    private int positivePoint;
    private int rarePoint;

    private BaseCheckType auctionStatus;
    private BaseCheckType isShow;
    private long hits;

}
