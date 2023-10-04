package dream.auction.dto.response;

import dream.auction.domain.Auction;
import dream.auction.domain.Bidding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBidding {

    private Long auctionId;
    private int biddingMoney;
    private LocalDateTime createdAt;
    private Long biddingUserId;
    private String biddingNickname;
    private int biddingCount;

    public static ResponseBidding from(Long auctionId, Bidding bidding, int biddingCount){

        ResponseBidding response = new ResponseBidding();
        response.auctionId = auctionId;
        response.biddingMoney = bidding.getBiddingMoney();
        response.createdAt = bidding.getCreatedAt();
        response.biddingUserId = bidding.getUser().getUserId();
        response.biddingNickname = bidding.getUser().getNickname();
        response.biddingCount = biddingCount;

        return response;
    }


}
