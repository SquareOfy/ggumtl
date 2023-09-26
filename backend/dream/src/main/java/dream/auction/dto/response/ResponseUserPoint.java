package dream.auction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserPoint {

    private Long userId;
    private int point;

    public static ResponseUserPoint from(Long userId, int point){

        ResponseUserPoint response = new ResponseUserPoint();
        response.userId = userId;
        response.point = point;

        return response;
    }
}
