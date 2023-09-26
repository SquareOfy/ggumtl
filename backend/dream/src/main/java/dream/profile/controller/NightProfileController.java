package dream.profile.controller;

import dream.common.domain.ResultTemplate;
import dream.profile.service.NightProfileService;
import dream.user.domain.User;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/night")
public class NightProfileController {

    private final UserService userService;

    private final NightProfileService nightProfileService;




    //개발용
    @GetMapping("/header/{profileUserId}")
    public ResultTemplate getNightHeader( @PathVariable Long profileUserId){
        User user = userService.getUserForDev(1L);

        return nightProfileService.getNightHeader(user, profileUserId);
    }

    @GetMapping("/card/{profileId}")
    public ResultTemplate getProfileCardTabList(@PathVariable Long profileId,
                                                @RequestParam(value = "lastItemId", required = false)
                                                Long lastItemId, @RequestParam("size") int size){
        User user = userService.getUserForDev(1L);
        return nightProfileService.getProfileCardTabList(user, profileId, lastItemId, size);

    }

    @GetMapping("/auction/list")
    public ResultTemplate getProfileAuctionList(@RequestParam(value = "lastItemId", required = false)
                                                       Long lastItemId, @RequestParam("size") int size){

        User user = userService.getUserForDev(1L);

        return nightProfileService.getProfileAuctionOnSaleList(user, lastItemId, size);
    }

    @GetMapping("/auction/participation/list")
    public ResultTemplate getProfileParticipatedAuctionList(@RequestParam(value = "lastItemId", required = false)
                                                            Long lastItemId, @RequestParam("size") int size){
        User user = userService.getUserForDev(1L);
        return nightProfileService.getProfileParticipatedAuctionList(user, lastItemId, size);

    }


    //배포용
//    @GetMapping("/header/{profileUserId}")
//    public ResultTemplate getNightHeader(@UserInfo User user, @PathVariable Long profileUserId){
//        return nightProfileService.getNightHeader(user, profileUserId);
//    }

//    @GetMapping("/card/{profileId}")
//    public ResultTemplate getProfileCardTabList(@UserInfo User user, @PathVariable Long profileId,
//                                                @RequestParam(value = "lastItemId", required = false)
//                                                Long lastItemId, @RequestParam("size") int size){
//
//        return nightProfileService.getListOfProfileCardTab(user, profileId, lastItemId, size);
//
//    }


}


