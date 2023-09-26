package dream.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDream {
    private String dream;
    private String similarDream;
    private String dreamTelling;
    private int positivePoint;

    public static ResponseDream from(String dream, String similarDream, String dreamTelling, int positivePoint) {

        ResponseDream response = new ResponseDream();

        response.dream = dream;
        response.similarDream = similarDream;
        response.dreamTelling = dreamTelling;
        response.positivePoint = positivePoint;

        return response;
    }
}
