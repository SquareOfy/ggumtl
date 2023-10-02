package dream.card.dto.response;

import dream.mongo.domain.Dream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamAndDreamTelling {

    private String dream;
    private String dreamTelling;


    public static ResponseDreamAndDreamTelling from(Dream dream){

        ResponseDreamAndDreamTelling response = new ResponseDreamAndDreamTelling();
        response.dream = dream.getDream();
        response.dreamTelling = dream.getAnalysis().getDreamTelling();

        return response;
    }
}
