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
public class ResponseDreamCardList {

    private Long dreamCardId;
    private Long dreamCardOwner;
    private String ownerNickname;
    private String ownerProfileUrl;
    private Long dreamCardAuthor;
    private LocalDateTime createAt;
    private int likedNumber;
    private boolean isLike;

    public static ResponseDreamCardList from(DreamCard dreamCard, boolean isLike){

        ResponseDreamCardList response = new ResponseDreamCardList();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardOwner = dreamCard.getDreamCardOwner().getUserId();
        response.ownerNickname = dreamCard.getDreamCardOwner().getNickname();
        response.ownerProfileUrl = dreamCard.getDreamCardOwner().getProfileUrl();
        response.dreamCardAuthor = dreamCard.getDreamCardAuthor().getUserId();
        response.createAt = dreamCard.getCreatedAt();
        response.likedNumber = dreamCard.getDreamCardLike().size();
        response.isLike = isLike;

        return response;
    }


}
