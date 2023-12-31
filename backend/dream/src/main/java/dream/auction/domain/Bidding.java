package dream.auction.domain;


import dream.card.domain.DreamCard;
import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bidding extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biddingId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "auction_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Auction auction;

    private int biddingMoney;


    public static Bidding insertFirstBidding(Auction auction){

        Bidding bidding = new Bidding();
        bidding.user = auction.getDreamCard().getDreamCardOwner();
        bidding.auction = auction;
        bidding.biddingMoney = auction.getStartAuctionMoney();

        return bidding;
    }

    public static Bidding insertBidding(Auction auction, User user, int biddingMoney){

        Bidding bidding = new Bidding();

        bidding.auction = auction;
        bidding.user = user;
        bidding.biddingMoney = biddingMoney;
        return bidding;
    }

}
