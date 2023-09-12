package dream.challenge.dto.response;

import com.querydsl.codegen.Keywords;
import dream.card.domain.DreamKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseKeyword {
    private long keywordId;
    private String keyword;

    public static ResponseKeyword from(DreamKeyword keyword){

        ResponseKeyword response = new ResponseKeyword();

        response.keywordId = keyword.getKeywordId();
        response.keyword = keyword.getKeyword();

        return response;
    }
}
