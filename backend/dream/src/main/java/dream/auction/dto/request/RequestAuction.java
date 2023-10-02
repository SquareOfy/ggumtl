package dream.auction.dto.request;

import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuction {
    private BaseCheckType auctionStatus;
    private BaseCheckType isShow;
    private LocalDateTime endedAt;
    private int immediatelyBuyMoney;
    private int startAuctionMoney;

}
