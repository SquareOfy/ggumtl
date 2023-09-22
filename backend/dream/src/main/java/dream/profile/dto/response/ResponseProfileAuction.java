package dream.profile.dto.response;

import dream.auction.domain.Auction;
import dream.card.domain.Grade;
import dream.card.dto.response.ResponseKeyword;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileAuction {
    private Long dreamCardId;
    private String dreamCardImageUrl;
    private Grade positivePoint;
    private Grade rarePoint;
    private List<String> keywords;
    private LocalDateTime endedAt;
    private BaseCheckType auctionStatus;


    public static ResponseProfileAuction from(Auction auction){
        ResponseProfileAuction resposne = new ResponseProfileAuction();
        resposne.dreamCardId = auction.getDreamCard().getDreamCardId();
        resposne.dreamCardImageUrl = auction.getDreamCard().getDreamCardImageUrl();
        resposne.positivePoint = auction.getDreamCard().getPositiveGrade();
        resposne.rarePoint = auction.getDreamCard().getRareGrade();
        resposne.keywords= auction.getDreamCard().getCardKeyword().stream().map(cardKeyword -> {
            return ResponseKeyword.from(cardKeyword).getKeyword();
        }).collect(Collectors.toList());

        return resposne;
    }
}
