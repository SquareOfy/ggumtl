package dream.challenge.controller;

import dream.challenge.service.ChallengeService;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/day")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final UserRepository userRepository;

    @GetMapping(value = "/")
    public ResultTemplate getDayMain(@RequestParam(value = "keywordId", required = false) Long keywordId,
                                      @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                      @RequestParam("size") int size){
        return challengeService.getDayMain(keywordId, lastItemId, size);
    }

    @GetMapping(value = "/keyword/list")
    public ResultTemplate getAllCategory(){
        return challengeService.getAllCategory();
    }

    @GetMapping(value = "/challenge/story/user-list")
    public ResultTemplate getFollowUserStory(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                             @RequestParam(value = "size") int size){

        User user = userRepository.findByUserId(1L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getFollowUserStory(user, lastItemId, size);
    }
}
