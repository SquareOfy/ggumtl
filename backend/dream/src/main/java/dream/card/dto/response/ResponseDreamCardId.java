package dream.card.dto.response;

import dream.card.domain.DreamCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardId {
    private Long dreamCardId;

    public static ResponseDreamCardId from(DreamCard dreamCard){
        ResponseDreamCardId response = new ResponseDreamCardId();
        response.dreamCardId = dreamCard.getDreamCardId();

        return response;
    }
}
