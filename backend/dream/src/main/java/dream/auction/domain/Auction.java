package dream.auction.domain;

import dream.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction extends BaseTimeEntity {

    @Id @JoinColumn(name = "dream_card_id")
    private Long dreamCardId;

    private int startAuctionMoney;
    private int immediatelyBuyMoney;
    private int askingMoney;

    private LocalDateTime endedAt;
}
