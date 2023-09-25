package dream.card.controller;


import dream.card.dto.request.RequestDreamCardContent;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardId;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.service.DreamCardService;
import dream.common.domain.ResultTemplate;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/night")
@RequiredArgsConstructor
public class DreamCardController {

    private final DreamCardService dreamCardService;

    @GetMapping(value = "/")
    public ResultTemplate getNightMain(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                       @RequestParam("size") int size,
                                       @UserInfo User user){
        return dreamCardService.getNightMain(lastItemId, size);
    }

    @GetMapping(value = "/dream/detail/{dreamCardId}")
    public ResultTemplate getFlipDreamCardDetail(@PathVariable("dreamCardId") Long id){

        return dreamCardService.getFlipDreamCardDetail(id);
    }
    @GetMapping(value = "/dream/userInfo/{dreamCardId}")
    public ResultTemplate getDreamCardUserInfo(@PathVariable("dreamCardId") Long id){

        return dreamCardService.getDreamCardUserInfo(id);
    }

    @PutMapping(value = "/dream/detail/hit")
    public ResultTemplate updateDreamCardHit(@RequestBody RequestDreamCardId request) {

        return dreamCardService.updateDreamCard(request.getDreamCardId());
    }

    @PostMapping(value = "/dream/like")
    public ResultTemplate updateCardLike(@RequestBody RequestDreamCardId request) {

        return dreamCardService.updateCardLike(1L, request.getDreamCardId());
    }

    @DeleteMapping(value = "/dream/{dreamCardId}/unlike")
    public ResultTemplate updateCardUnlike(@PathVariable("dreamCardId") Long dreamCardId){

        return dreamCardService.updateCardUnlike(1L, dreamCardId);
    }

    @PostMapping(value = "/dream/keyword")
    public ResultTemplate getPreProcessingForDreamCard(@RequestBody RequestDreamCardContent request){

        return dreamCardService.getPreProcessingForDreamCard(request.getDreamCardContent());
    }

    @GetMapping(value = "/dream/{dreamCardId}/interpretation")
    public ResultTemplate getDreamCardDetailByUser(@PathVariable("dreamCardId") Long id,
                                                   @UserInfo User user){
        return dreamCardService.getDreamCardDetailByUser(id, user.getUserId());
    }
    @DeleteMapping(value = "/dream/{dreamCardId}")
    public ResultTemplate deleteDreamCard(@PathVariable("dreamCardId") Long dreamCardId,
                                          @UserInfo User user){

        return dreamCardService.deleteDreamCard(dreamCardId, user.getUserId());
    }

    @PutMapping(value = "/dream")
    public ResultTemplate updateCardIsShow(@RequestBody RequestDreamCardIsShow request,
                                           @UserInfo User user){

        return dreamCardService.updateCardIsShow(request, user.getUserId());
    }

    @GetMapping(value = "/dream/interpretation?keyword={keyword}")
    public ResultTemplate getInterpretationResult(@RequestParam("keyword") String keyword){

        return dreamCardService.getInterpretationResult(keyword);
    }
}
