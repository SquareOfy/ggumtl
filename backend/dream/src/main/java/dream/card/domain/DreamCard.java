package dream.card.domain;


import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private List<DreamCardLike> dreamCardLike;

    @OneToMany(mappedBy = "cardId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardKeyword> cardKeyword;



}
