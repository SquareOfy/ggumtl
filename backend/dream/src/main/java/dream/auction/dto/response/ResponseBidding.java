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
    private int askingMoney;

    public static ResponseBidding from(Long auctionId, Bidding bidding){

        ResponseBidding response = new ResponseBidding();
        response.auctionId = auctionId;
        response.biddingMoney = bidding.getBiddingMoney();
        response.createdAt = bidding.getCreatedAt();
        response.biddingUserId = bidding.getUser().getUserId();
        response.biddingNickname = bidding.getUser().getNickname();
        int start = bidding.getBiddingMoney();
        int count = 0;
        while (start >= 10) {
            start /= 10;
            count++;
        }
        response.askingMoney = start * (int)Math.pow(10, count - 1);

        return response;
    }


}
