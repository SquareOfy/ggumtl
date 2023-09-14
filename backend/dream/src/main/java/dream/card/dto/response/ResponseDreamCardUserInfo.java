package dream.card.dto.response;

import dream.card.domain.DreamCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDreamCardUserInfo {

    private Long dreamCardAuthor;
    private Long dreamCardOwner;


    public static ResponseDreamCardUserInfo from(DreamCard dreamCard){
        ResponseDreamCardUserInfo response = new ResponseDreamCardUserInfo();

        response.dreamCardAuthor = dreamCard.getDreamCardAuthor().getUserId();
        response.dreamCardOwner = dreamCard.getDreamCardOwner().getUserId();

        return response;
    }
}
