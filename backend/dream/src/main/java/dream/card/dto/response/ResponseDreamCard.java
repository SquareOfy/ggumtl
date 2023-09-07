package dream.card.dto.response;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.card.domain.Grade;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCard {

    private long dreamCardId;
    private ResponseUser dreamCardOwner;
    private ResponseUser dreamCardAuthor;
    private String dreamCardContent;
    private String dreamCardImageUrl;
    private Grade grade;
    private LocalDateTime createdAt;
    private String dreamTelling;
    private int positivePoint;
    private int rarePoint;
    private BaseCheckType auctionStatus;
    private BaseCheckType isShow;
    private long hits;
    private List<ResponseLike> dreamCardLike;

    public static ResponseDreamCard from(DreamCard dreamCard){

        ResponseDreamCard response = new ResponseDreamCard();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardAuthor = ResponseUser.from(dreamCard.getDreamCardAuthor());
        response.dreamCardAuthor = ResponseUser.from(dreamCard.getDreamCardAuthor());
        response.dreamCardContent = dreamCard.getDreamCardContent();

        List<ResponseLike> list = new ArrayList<>();
        List<DreamCardLike> cardLikes = dreamCard.getDreamCardLike();
        for (DreamCardLike cardLike : cardLikes) {
            list.add(ResponseLike.from(cardLike));
        }
        response.dreamCardLike = list;

        return response;
    }


}
