package dream.card.dto.request;

import dream.card.domain.Grade;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDreamCardDetail {
    private String dreamCardContent;
    private Long dreamCardAuthor;
    private int positivePoint;
    private int negativePoint;
    private BaseCheckType isShow;
    private List<String> keywords;
    private List<String> wordKeywords;
}
