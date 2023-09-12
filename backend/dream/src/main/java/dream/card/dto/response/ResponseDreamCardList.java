package dream.card.dto.response;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.card.domain.Grade;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardList {

    private List<ResponseDreamCard> list;
    private boolean hasNext;

    public static ResponseDreamCardList from(List<ResponseDreamCard> responseDreamCardList, boolean hasNext){

        ResponseDreamCardList response = new ResponseDreamCardList();
        response.list = responseDreamCardList;
        response.hasNext = hasNext;

        return response;
    }


}
