package dream.card.dto.response;

import dream.card.domain.CardKeyword;
import dream.card.domain.DreamKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseKeyword {

    private String keyword;

    public static ResponseKeyword from(CardKeyword keyword){

        ResponseKeyword response = new ResponseKeyword();
        response.keyword = keyword.getKeyWordId().getKeyword();

        return response;
    }
}
