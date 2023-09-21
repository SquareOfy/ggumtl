package dream.auction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBidding {
    private Long auctionId;
    private int biddingMoney;
    private Long userId;
}
