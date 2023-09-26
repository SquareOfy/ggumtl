package dream.card.dto.response;

import dream.card.domain.DreamCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardImage {
    private String imageUrl;

    public static ResponseDreamCardImage from(DreamCard dreamCard) {

        ResponseDreamCardImage response = new ResponseDreamCardImage();
        response.imageUrl = dreamCard.getDreamCardImageUrl();

        return response;
    }
}
