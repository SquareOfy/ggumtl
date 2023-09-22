package dream.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileChallengeBadgeList {

    private List<ResponseProfileChallengeBadge> badgeList;
    private boolean hasNext;


    public static ResponseProfileChallengeBadgeList from(List<ResponseProfileChallengeBadge> list, boolean hasNext){
        ResponseProfileChallengeBadgeList response = new ResponseProfileChallengeBadgeList();

        response.badgeList = list;
        response.hasNext = hasNext;

        return response;

    }

}
