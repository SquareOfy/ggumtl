package dream.card.dto.response;

import dream.card.domain.DreamCardLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLike {

    private Long dreamCardLikeId;

    public static ResponseLike from(DreamCardLike dreamCardLike){
        ResponseLike response = new ResponseLike();
        response.dreamCardLikeId = dreamCardLike.getDreamCardLikeId();

        return response;
    }
}
