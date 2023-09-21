package dream.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentsssss {

    private List<ResponseComment> resultList;
    private boolean hasNext;

    public static ResponseCommentsssss from(List<ResponseComment> commentList, boolean hasNext) {

        ResponseCommentsssss response = new ResponseCommentsssss();

        response.resultList = commentList;
        response.hasNext = hasNext;

        return response;
    }
}
