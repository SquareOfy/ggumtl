package dream.card.dto.response;

import dream.card.domain.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardPreprocessing {

    private List<ResponseKeyword> keywords;
    private Grade grade;
    private String dreamTelling;
    private int positivePoint;
    private int rarePoint;
    private Grade positiveGrade;
    private Grade rareGrade;

    public static ResponseDreamCardPreprocessing from(){
        ResponseDreamCardPreprocessing response = new ResponseDreamCardPreprocessing();

        return response;
    }
}
