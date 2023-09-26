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
    private String dreamCardContent;   // 꿈 내용
    private Long dreamCardAuthor;      // 꿈 작성자 ID
    private int positivePoint;         // 꿈 긍정도
    private int negativePoint;         // 꿈 부정도
    private BaseCheckType isShow;      // 꿈 공개여부 Default = "F"
    private List<String> keywords;     // 꿈 키워드 (ex - "사랑", "우정", "행복")
    private List<String> wordKeywords; // 꿈 주요 단어 키워드들 ( "비둘기", "방", "들어가다" )
}
