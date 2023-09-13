package dream.card.controller;


import dream.card.dto.request.RequestDreamCardContent;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardId;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.service.DreamCardService;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/night")
@RequiredArgsConstructor
public class DreamCardController {

    private final DreamCardService dreamCardService;

    @GetMapping(value = "/")
    public ResultTemplate getNightMain(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                       @RequestParam("size") int size){
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

    @PostMapping(value = "/dream/new")
    public ResultTemplate postDreamCard(@RequestBody RequestDreamCardDetail request){

        return dreamCardService.postDreamCard(request);
    }

    @GetMapping(value = "/dream/{dreamCardId}/interpretation")
    public ResultTemplate getDreamCardDetailByUser(@PathVariable("dreamCardId") long dreamCardId){

        return dreamCardService.getDreamCardDetailByUser(dreamCardId);
    }

    @DeleteMapping(value = "/dream/{dreamCardId}")
    public ResultTemplate deleteDreamCard(@PathVariable("dreamCardId") long dreamCardId){

        return dreamCardService.deleteDreamCard(dreamCardId);
    }

    @PutMapping(value = "/dream")
    public ResultTemplate updateCardIsShow(@RequestBody RequestDreamCardIsShow request){

        return dreamCardService.updateCardIsShow(request);
    }

    @GetMapping(value = "/dream/interpretation?keyword={keyword}")
    public ResultTemplate getInterpretationResult(@RequestParam("keyword") String keyword){

        return dreamCardService.getInterpretationResult(keyword);
    }
}
