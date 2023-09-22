package dream.auction.dto.response;

import dream.auction.domain.Auction;
import dream.auction.domain.Bidding;
import dream.card.domain.CardKeyword;
import dream.card.domain.Grade;
import dream.card.dto.response.ResponseKeyword;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jndi.JndiLocatorDelegate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuctionDetail {
    private Long biddingId;
    private Long userId;
    private int biddingMoney;
    private LocalDateTime createdAt;
    private String nickname;
    private int startAuctionMoney;
    private int immediatelyBuyMoney;
    private LocalDateTime endedAt;
    private int askingMoney;
    private int biddingCount;
    private Long dreamCardId;
    private String dreamCardImageUrl;
    private List<ResponseKeyword> keywords;
    private Grade positiveGrade;
    private Grade rareGrade;
    private BaseCheckType auctionStatus;

    public static ResponseAuctionDetail from(Auction auction, List<Bidding> biddingList){

        ResponseAuctionDetail response = new ResponseAuctionDetail();

        Bidding bidding = biddingList.get(0);
        response.biddingId = bidding.getBiddingId();
        response.userId = bidding.getUser().getUserId();
        response.biddingMoney = bidding.getBiddingMoney();
        response.createdAt = bidding.getCreatedAt();
        response.nickname = bidding.getUser().getNickname();
        response.startAuctionMoney = auction.getStartAuctionMoney();
        response.immediatelyBuyMoney = auction.getImmediatelyBuyMoney();
        response.endedAt = auction.getEndedAt();
        response.askingMoney = auction.getAskingMoney();
        response.biddingCount = biddingList.size();
        response.dreamCardId = auction.getDreamCard().getDreamCardId();
        response.dreamCardImageUrl = auction.getDreamCard().getDreamCardImageUrl();

        List<ResponseKeyword> keywords = new ArrayList<>();
        List<CardKeyword> cardKeywords = auction.getDreamCard().getCardKeyword();
        for (CardKeyword cardKeyword : cardKeywords) {
            keywords.add(ResponseKeyword.from(cardKeyword));
        }
        response.keywords = keywords;
        response.positiveGrade = auction.getDreamCard().getPositiveGrade();;
        response.rareGrade = auction.getDreamCard().getRareGrade();
        response.auctionStatus = auction.getDreamCard().getAuctionStatus();

        return response;
    }
}
