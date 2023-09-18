package dream.auction.controller;

import dream.auction.dto.request.RequestAuction;
import dream.auction.dto.request.RequestBidding;
import dream.auction.dto.request.RequestCardReview;
import dream.auction.dto.request.RequestChangeOwner;
import dream.auction.service.AuctionService;
import dream.card.dto.request.RequestDreamCardId;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping(value = "/{dreamCardId}")
    public ResultTemplate postAuction(@PathVariable("dreamCardId") Long dreamCardId,
                                      @RequestBody RequestAuction request,
                                      Long userId){

        return auctionService.postAuction(dreamCardId, request, 1L);
    }

    @GetMapping(value = "/list")
    public ResultTemplate getAllAuctionList(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                            @RequestParam("size") int size,
                                            @RequestParam(value = "keyword", required = false) String keyword){

        return auctionService.getAllAuctionList(lastItemId, size, keyword);
    }

    @GetMapping(value = "/list/{searchKeyword}")
    public ResultTemplate getAuctionListByKeyword(@PathVariable("searchKeyword") String keyword){

        return auctionService.getAuctionListByKeyword(keyword);
    }

    @GetMapping(value = "/detail/{dreamCardId}")
    public ResultTemplate getAuctionDetail(@PathVariable("dreamCardId") long dreamCardId){

        return auctionService.getAuctionDetail(dreamCardId);
    }

    @PostMapping(value = "/bid")
    public ResultTemplate postBidding(@RequestBody RequestBidding request){

        // 유저 받아와서 같이 넘겨주세요
        return auctionService.postBidding(request);
    }

    @PutMapping(value = "/purchase")
    public ResultTemplate purchaseDreamCard(@RequestBody RequestDreamCardId request){

        // 유저 받아와서 같이 넘겨주세요
        return auctionService.purchaseDreamCard(request);
    }

    // 매핑 URL 체크 가능성 존재
    @PutMapping(value = "/")
    public ResultTemplate successBiddingAndOwnerChange(@RequestBody RequestChangeOwner request) {

        // 유저 받아와서 같이 넘겨주세요
        return auctionService.successBiddingAndOwnerChange(request);
    }

    @PostMapping(value = "/review")
    public ResultTemplate postBuyingCardReview(@RequestBody RequestCardReview request){

        return auctionService.postBuyingCardReview(request);
    }

}
