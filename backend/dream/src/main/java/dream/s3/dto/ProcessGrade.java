package dream.s3.dto;

import dream.card.domain.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessGrade {
    private Grade positiveDreamGrade;
    private Grade rareDreamGrade;
    private Grade finalGrade;
}
