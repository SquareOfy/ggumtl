package dream.auction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuctionList {

    private List<ResponseAuction> list;
    private boolean hasNext;

    public static ResponseAuctionList from(List<ResponseAuction> responseAuctionList, boolean hasNext){
        ResponseAuctionList response = new ResponseAuctionList();
        response.list = responseAuctionList;
        response.hasNext = hasNext;

        return response;
    }

}
