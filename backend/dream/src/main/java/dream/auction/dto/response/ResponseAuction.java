package dream.auction.dto.response;

import dream.auction.domain.Auction;
import dream.card.domain.CardKeyword;
import dream.card.domain.Grade;
import dream.card.dto.response.ResponseKeyword;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuction {
    private long auctionId;
    private long dreamCardId;
    private Grade grade;
    private Grade positiveGrade;
    private Grade rareGrade;
    private List<ResponseKeyword> keywords;
    private LocalDateTime endedAt;
    private BaseCheckType auctionStatus;
    private String dreamCardImageUrl;
    public static ResponseAuction from(Auction auction){

        ResponseAuction response = new ResponseAuction();
        response.auctionId = auction.getAuctionId();
        response.dreamCardId = auction.getDreamCard().getDreamCardId();
        response.grade = auction.getDreamCard().getGrade();
        response.positiveGrade = auction.getDreamCard().getPositiveGrade();;
        response.rareGrade = auction.getDreamCard().getRareGrade();

        List<ResponseKeyword> keywords = new ArrayList<>();
        List<CardKeyword> cardKeywords = new ArrayList<>();

        List<CardKeyword> temp = auction.getDreamCard().getCardKeyword();
        for (CardKeyword cardKeyword : temp) {
            if (!cardKeywords.contains(cardKeyword)) cardKeywords.add(cardKeyword);
        }

        for (CardKeyword cardKeyword : cardKeywords) {
            keywords.add(ResponseKeyword.from(cardKeyword));
        }
        response.keywords = keywords;
        response.endedAt = auction.getEndedAt();
        response.auctionStatus = auction.getDreamCard().getAuctionStatus();
        response.dreamCardImageUrl = auction.getDreamCard().getDreamCardImageUrl();

        return response;
    }
}

