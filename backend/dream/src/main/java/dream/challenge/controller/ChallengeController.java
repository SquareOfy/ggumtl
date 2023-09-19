package dream.challenge.controller;

import dream.challenge.service.ChallengeService;
import dream.challenge.dto.request.RequestTimeCapsule;
import dream.challenge.dto.request.RequestChallengeId;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/day")
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
    public ResultTemplate getFollowUsers(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                         @RequestParam(value = "size") int size){

        User user = userRepository.findByUserId(1L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getFollowUsers(user, lastItemId, size);
    }

    @GetMapping(value = "/challange/story/{userId}")
    public ResultTemplate getFollowUserStory(@PathVariable("userId") long userId) {

        return challengeService.getFollowUserStory(userId);
    }

    @GetMapping(value = "/challenge/search/{searchKeyword}")
    public ResultTemplate searchChallenge(@PathVariable(value = "searchKeyword", required = false) String searchKeyword,
                                          @RequestParam(value = "keywordId", required = false) Long keywordId,
                                          @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                          @RequestParam(value = "size") int size) {

        return challengeService.getSearchedChallenge(searchKeyword, keywordId, lastItemId, size);
    }

    @GetMapping(value = "/challenge/item/{challangeId}")
    public ResultTemplate getChallengeInfo(@PathVariable(value = "challangeId") Long challangeId){

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getChallengeInfo(user, challangeId);
    }

    @PostMapping(value = "/challenge")
    public ResultTemplate postParticipateChallenge(@RequestBody RequestChallengeId request){

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postParicipateChallenge(user.getUserId(), request.getChallengeId());
    }


    @PutMapping(value = "/challenge/hits")
    public ResultTemplate updateChallengeHits(@RequestBody RequestChallengeId request){

        return challengeService.updateChallengeHits(request.getChallengeId());
    }

    @PostMapping(value = "/challenge/timecapsule")
    public ResultTemplate postTimeCapsule(@Valid  @RequestBody RequestTimeCapsule request){

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postTimeCapsule(user, request);
    }
}
