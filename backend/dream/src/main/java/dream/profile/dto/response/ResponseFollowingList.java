package dream.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFollowingList {

    private List<ResponseFollowingUserInfo> followList;
    private boolean hasNext;

    public static ResponseFollowingList from(List<ResponseFollowingUserInfo> followList, boolean hasNext){
        ResponseFollowingList response = new ResponseFollowingList();

        response.followList = followList;
        response.hasNext = hasNext;
        return response;
    }


}
