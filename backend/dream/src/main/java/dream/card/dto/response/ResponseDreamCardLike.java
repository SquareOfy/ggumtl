package dream.card.dto.response;

import dream.card.domain.DreamCardLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardLike {

    private Long dreamCardLikeId;

    public static ResponseDreamCardLike from(DreamCardLike dreamCardLike){
        ResponseDreamCardLike response = new ResponseDreamCardLike();
        response.dreamCardLikeId = dreamCardLike.getDreamCardLikeId();

        return response;
    }
}
