package dream.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileDreamCardTabList {

    private List<ResponseProfileDreamCardTab> dreamCardList;

    private boolean hasNext;
    public static ResponseProfileDreamCardTabList from(List<ResponseProfileDreamCardTab> list, boolean hasNext){

        ResponseProfileDreamCardTabList response = new ResponseProfileDreamCardTabList();

        response.dreamCardList = list;
        response.hasNext = hasNext;

        return response;
    }
}
