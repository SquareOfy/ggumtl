package dream.auction.domain;

import dream.auction.dto.request.RequestAuction;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private DreamCard dreamCard;

    private int startAuctionMoney;
    private int immediatelyBuyMoney;
    private int askingMoney;

    private LocalDateTime endedAt;


    public static Auction createAuction(DreamCard dreamCard, RequestAuction request){

        Auction auction = new Auction();
        auction.dreamCard = dreamCard;
        auction.startAuctionMoney = request.getStartAuctionMoney();
        auction.immediatelyBuyMoney = request.getImmediatelyBuyMoney();

        int start = request.getStartAuctionMoney();
        int count = 0;
        while (start >= 10) {
            start /= 10;
            count++;
        }
        auction.askingMoney = start * (int)Math.pow(10, count - 1);
        auction.endedAt = request.getEndedAt();

        return auction;
    }
}
