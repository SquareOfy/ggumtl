package dream.card.dto.response;

import dream.card.domain.DreamCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCard {

    private Long dreamCardId;
    private Long dreamCardOwner;
    private String ownerNickname;
    private String ownerProfileUrl;
    private Long dreamCardAuthor;
    private LocalDateTime createAt;
    private int likedNumber;
    private boolean isLike;

    public static ResponseDreamCard from(DreamCard dreamCard, boolean isLike){

        ResponseDreamCard response = new ResponseDreamCard();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardOwner = dreamCard.getDreamCardOwner().getUserId();
        response.ownerNickname = dreamCard.getDreamCardOwner().getNickname();
        response.ownerProfileUrl = dreamCard.getDreamCardOwner().getProfileUrl();
        response.dreamCardAuthor = dreamCard.getDreamCardAuthor().getUserId();
        response.createAt = dreamCard.getCreatedAt();
        response.likedNumber = dreamCard.getDreamCardLikes().size();
        response.isLike = isLike;

        return response;
    }


}
