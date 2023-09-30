package dream.profile.controller;

import dream.common.domain.ResultTemplate;
import dream.profile.service.DayProfileService;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/day")
public class DayProfileController {


    private final UserService userService;
    private final DayProfileService dayProfileService;

//통합된 api
//    @GetMapping("/header/{profileUserId}")
//    public ResultTemplate getDayHeader(@PathVariable Long profileUserId, @UserInfo User user){
////        User user = userService.getUserForDev(20L);
//
//        return dayProfileService.getDayHeader(user, profileUserId);
//    }

    @GetMapping("/badge/list/{profileUserId}")
    public ResultTemplate getBadgeList(@PathVariable Long profileUserId,
                                       @RequestParam(value = "lastItemId", required = false)
                                           Long lastItemId, @RequestParam("size") int size){

        return dayProfileService.getProfileBadgeList(profileUserId, lastItemId, size);
    }

    @GetMapping("badge/detail/{badgeId}")
    public ResultTemplate getBadgeDetail(@PathVariable Long badgeId){
        return dayProfileService.getProfileBadgeDetail(badgeId);
    }

    @GetMapping("/mychallenge/end/list/{profileUserId}")
    public ResultTemplate getFinishedChallengeList(@PathVariable Long profileUserId,
                                       @RequestParam(value = "lastItemId", required = false)
                                       Long lastItemId, @RequestParam("size") int size){

        return dayProfileService.getFinishedChallengeListByProfileUser(profileUserId, lastItemId, size);
    }




}
