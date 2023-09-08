package dream.card.dto.response;

import dream.card.domain.DreamKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseKeyword {

    private String keyword;

    public static ResponseKeyword from(DreamKeyword keyword){

        ResponseKeyword response = new ResponseKeyword();
        response.keyword = keyword.getKeyword();

        return response;
    }
}
