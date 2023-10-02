package dream.challenge.controller;

import dream.challenge.domain.ChallengeDetail;
import dream.challenge.dto.request.*;
import dream.challenge.service.ChallengeService;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import dream.security.jwt.domain.UserInfo;
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

    @GetMapping()
    public ResultTemplate getDayMain(@RequestParam(value = "keywordId", required = false) Long keywordId,
                                     @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                     @RequestParam("size") int size) {
        return challengeService.getDayMain(keywordId, lastItemId, size);
    }

    @GetMapping("/recommend")
    public ResultTemplate getRecommendChallenge(@UserInfo User user) {
        return challengeService.getRecommendChallenge(user.getUserId());
    }


    @GetMapping(value = "/keyword/list")
    public ResultTemplate getAllCategory() {
        return challengeService.getAllCategory();
    }

    @GetMapping(value = "/challenge/story/user-list")
    public ResultTemplate getFollowUsers(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                         @RequestParam(value = "size") int size, @UserInfo User user) {

//        User user = userRepository.findByUserId(3L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

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
    public ResultTemplate getChallengeInfo(@PathVariable(value = "challangeId") Long challangeId, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getChallengeInfo(user, challangeId);
    }

    @PostMapping(value = "/challenge")
    public ResultTemplate postParticipateChallenge(@RequestBody RequestChallengeId request, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postParicipateChallenge(user.getUserId(), request.getChallengeId());
    }

    @PutMapping(value = "/challenge/hits")
    public ResultTemplate updateChallengeHits(@RequestBody RequestChallengeId request) {

        return challengeService.updateChallengeHits(request.getChallengeId());
    }

    @PostMapping(value = "/challenge/timecapsule")
    public ResultTemplate postTimeCapsule(@Valid @RequestBody RequestTimeCapsule request, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postTimeCapsule(user, request);
    }

    @GetMapping(value = "/challenge/writeDetailPossible/{challengeId}")
    public ResultTemplate writeDetailPossible(@PathVariable("challengeId") Long challengeId, @UserInfo User user) {
//
//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.writeDetailPossible(user, challengeId);
    }

    @PostMapping(value = "/challenge/new")
    public ResultTemplate postChallenge(@RequestBody RequestChallenge request, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Long challengeId = challengeService.postChallenge(user, request);

        return challengeService.postChallengeKeyword(challengeId, request);
    }

    @GetMapping(value = "/challenge/detail/{detailId}/comment")
    public ResultTemplate getComments(@PathVariable("detailId") Long detailId,
                                      @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                      @RequestParam("size") int size) {

        return challengeService.getComments(detailId, lastItemId, size);
    }

    @PostMapping(value = "/challenge/detail/comment")
    public ResultTemplate postComments(@RequestBody RequestComment request, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postComment(user, request);
    }

    @DeleteMapping(value = "/challenge/detail/comment/{commentId}")
    public ResultTemplate deleteComment(@PathVariable("commentId") Long commentId) {

        return challengeService.deleteComment(commentId);
    }

    @PostMapping(value = "/challenge/detail/like")
    public ResultTemplate postLike(@RequestBody RequestChallengeDetailId request, @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postLike(user, request.getChallengeDetailId());
    }

    @DeleteMapping(value = "/challenge/detail/{challengeDetailId}/unlike")
    public ResultTemplate deleteLike(@PathVariable("challengeDetailId") Long challengeDetailId
            , @UserInfo User user) {

//        User user = userRepository.findByUserId(2L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.postUnLike(user, challengeDetailId);
    }

    @GetMapping(value = "/challenge/detail/{challengeId}/list")
    public ResultTemplate getChallengeDetails(@PathVariable("challengeId") Long challengeId,
                                              @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                              @RequestParam("size") int size, @UserInfo User user) {

//        User user = userRepository.findByUserId(3L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getChallengeDetails(user, challengeId, lastItemId, size);
    }

    @GetMapping(value = "/mychallenge/list")
    public ResultTemplate getMyChallengeList(@RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                             @RequestParam("size") int size, @UserInfo User user) {

//        User user = userRepository.findByUserId(3L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getMyChallengeList(user, lastItemId, size);
    }

    @GetMapping(value = "/challenge/{challengeDetailId}")
    public ResultTemplate getMyChallengeInfo(@PathVariable("challengeDetailId") Long challengeMidId
            , @UserInfo User user) {

//        User user = userRepository.findByUserId(3L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getMyChallengeInfo(user, challengeMidId);
    }

    @GetMapping(value = "/challenge/timecapsule/{challengeId}")
    public ResultTemplate getTimeCapsules(@PathVariable("challengeId") Long challengeId,
                                          @RequestParam(value = "lastItemId", required = false) Long lastItemId,
                                          @RequestParam(value = "size") int size
                                        , @UserInfo User user) {

//        User user = userRepository.findByUserId(30L).
//                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return challengeService.getTimeCapsule(user, challengeId, lastItemId, size);
    }
}
