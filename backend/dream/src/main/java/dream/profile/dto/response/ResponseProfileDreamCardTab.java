package dream.profile.dto.response;

import dream.card.domain.DreamCard;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileDreamCardTab {

    private Long dreamCardId;
    private String dreamCardImageUrl;
    private Long dreamCardAuthorId;
    private BaseCheckType isShow;
    private boolean isPurchase;

    public static ResponseProfileDreamCardTab from(DreamCard dreamCard){
        ResponseProfileDreamCardTab response = new ResponseProfileDreamCardTab();

        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardImageUrl = dreamCard.getDreamCardImageUrl();
        response.dreamCardAuthorId = dreamCard.getDreamCardAuthor().getUserId();
        response.isShow = dreamCard.getIsShow();

        if(dreamCard.getDreamCardAuthor().getUserId() == dreamCard.getDreamCardOwner().getUserId()) response.isPurchase = false;
        else response.isPurchase = true;
        return response;
    }

}
