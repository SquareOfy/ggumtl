package dream.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFollowerList {
    private List<ResponseFollowerUserInfo> followList;
    private boolean hasNext;

    public static ResponseFollowerList from(List<ResponseFollowerUserInfo> followList, boolean hasNext){
        ResponseFollowerList response = new ResponseFollowerList();

        response.followList = followList;
        response.hasNext = hasNext;
        return response;
    }


}

