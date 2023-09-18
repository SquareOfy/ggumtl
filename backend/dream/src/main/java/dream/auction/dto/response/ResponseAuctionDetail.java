package dream.auction.dto.response;

import dream.card.domain.Grade;
import dream.card.dto.response.ResponseKeyword;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jndi.JndiLocatorDelegate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuctionDetail {
    private long biddingId;
    private long userId;
    private int biddingMoney;
    private LocalDateTime biddingAt;
    private String nickname;
    private int startAuctionMoney;
    private int immediatelyBuyMoney;
    private LocalDateTime endedAt;
    private int askingMoney;
    private int biddingCount;
    private long dreamCardId;
    private String dreamCardImageUrl;
    private List<ResponseKeyword> keywords;
    private Grade positivePoint;
    private Grade rarePoint;
    private BaseCheckType auctionStatus;
}
