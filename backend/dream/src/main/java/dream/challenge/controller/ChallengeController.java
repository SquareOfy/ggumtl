package dream.challenge.controller;

import dream.challenge.service.ChallengeService;
import dream.common.domain.ResultTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/day")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/")
    private ResultTemplate getDayMain(@RequestParam(value = "keywordId", required = false) Long keywordId,
                                      @RequestParam("lastItemId") Long lastItemId,
                                      @RequestParam("size") int size){
        return challengeService.getDayMain(keywordId, lastItemId, size);
    }


}
