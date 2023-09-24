package dream.card.dto.response;

import dream.card.domain.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamAnalysis {
    private int rarePoint;
    private Grade grade;
    private String dreamTelling;
    private Grade positiveGrade;
    private Grade rareGrade;
}
