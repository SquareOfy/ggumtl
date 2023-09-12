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
    private long dreamCardAuthor;
    private String dreamCardImageUrl;
    private Grade grade;
    private String dreamTelling;
    private int positivePoint;
    private int rarePoint;
    private BaseCheckType isShow;
    private List<RequestKeyword> keywords;
}
