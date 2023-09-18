package dream.card.domain;


import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WriggleReview extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wriggleReviewId;

    @JoinColumn(name = "dream_card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DreamCard dreamCard;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User sellerId;

    @JoinColumn(name = "buyer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User buyerId;

    private String reviewContent;
    private int reviewPoint;

    @Enumerated(EnumType.STRING)
    private BaseCheckType reviewStatus;

}
