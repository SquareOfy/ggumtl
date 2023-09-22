package dream.profile.dto.response;

import dream.auction.domain.Auction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileAuctionList {

    private List<ResponseProfileAuction> auctionList;

    private boolean hasNext;


    public static ResponseProfileAuctionList from(List<ResponseProfileAuction> list, boolean hasNext){

        ResponseProfileAuctionList response = new ResponseProfileAuctionList();

        response.auctionList = list;
        response.hasNext = hasNext;

        return response;

    }
}
