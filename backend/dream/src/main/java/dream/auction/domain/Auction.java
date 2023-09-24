package dream.auction.domain;

import dream.auction.dto.request.RequestAuction;
import dream.card.domain.DreamCard;
import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bidding> bidding = new ArrayList<>();


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
        auction.bidding.add(Bidding.insertFirstBidding(auction));

        return auction;
    }

    public void addBidding(User user, int biddingMoney, int askingMoney){
        bidding.add(Bidding.insertBidding(this, user, biddingMoney));
        this.askingMoney = askingMoney;
    }

    public void lastBidding(User user, int biddingMoney){
        bidding.add(Bidding.insertBidding(this, user, biddingMoney));
        dreamCard.endAuction(user);
    }
}
