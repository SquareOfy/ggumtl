package dream.auction.controller;

import dream.auction.dto.request.RequestAuction;
import dream.auction.dto.request.RequestBidding;
import dream.auction.dto.request.RequestCardReview;
import dream.auction.dto.request.RequestChangeOwner;
import dream.auction.service.AuctionService;
import dream.card.dto.request.RequestDreamCardId;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping(value = "/api/auction/{dreamCardId}")
    public ResultTemplate postAuction(@PathVariable("dreamCardId") Long dreamCardId,
                                      @RequestBody RequestAuction request,
                                      Long userId){

        return auctionService.postAuction(dreamCardId, request, 1L);
    }

    @GetMapping(value = "/api/auction/list")
    public ResultTemplate getAllAuctionList(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                            @RequestParam("size") int size,
                                            @RequestParam(value = "keyword", required = false) String keyword){

        return auctionService.getAllAuctionList(lastItemId, size, keyword);
    }

    @GetMapping(value = "/api/auction/list/{searchKeyword}")
    public ResultTemplate getAuctionListByKeyword(@PathVariable("searchKeyword") String keyword){

        return auctionService.getAuctionListByKeyword(keyword);
    }

    @GetMapping(value = "/api/auction/detail/{auctionId}")
    public ResultTemplate getAuctionDetail(@PathVariable("auctionId") Long auctionId){

        return auctionService.getAuctionDetail(auctionId);
    }

    @MessageMapping(value = "/auction/bidding")
    public void postBidding(@RequestBody RequestBidding request){
        // 유저 받아와서 같이 넘겨주세요
        auctionService.postBidding(request);
    }

    @PutMapping(value = "/api/auction/purchase")
    public ResultTemplate purchaseDreamCard(@RequestBody RequestBidding request){

        return auctionService.purchaseDreamCard(request);
    }

    // 매핑 URL 체크 가능성 존재
    @PutMapping(value = "/api/auction")
    public ResultTemplate successBiddingAndOwnerChange(@RequestBody RequestChangeOwner request) {

        // 유저 받아와서 같이 넘겨주세요
        return auctionService.successBiddingAndOwnerChange(request);
    }

    @PostMapping(value = "/api/auction/review")
    public ResultTemplate postBuyingCardReview(@RequestBody RequestCardReview request){

        return auctionService.postBuyingCardReview(request);
    }

}
