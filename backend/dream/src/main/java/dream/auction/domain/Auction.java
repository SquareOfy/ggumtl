package dream.auction.domain;

import dream.card.domain.DreamCard;
import dream.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    @JoinColumn(name = "dream_card_id")
    @OneToOne(fetch = FetchType.LAZY)
    private DreamCard dreamCard;

    private int startAuctionMoney;
    private int immediatelyBuyMoney;
    private int askingMoney;

    private LocalDateTime endedAt;
}
