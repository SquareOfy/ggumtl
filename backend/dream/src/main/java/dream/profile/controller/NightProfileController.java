package dream.profile.controller;

import dream.common.domain.ResultTemplate;
import dream.profile.service.NightProfileService;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/night")
public class NightProfileController {

    private final UserService userService;

    private final NightProfileService nightProfileService;



//통합됨
//    @GetMapping("/header/{profileUserId}")
//    public ResultTemplate getNightHeader( @PathVariable Long profileUserId, @UserInfo User user){
//
//        return nightProfileService.getNightHeader(user, profileUserId);
//    }

    @GetMapping("/card/{profileId}")
    public ResultTemplate getProfileCardTabList(@PathVariable Long profileId,
                                                @RequestParam(value = "lastItemId", required = false)
                                                Long lastItemId, @RequestParam("size") int size,
                                                @UserInfo User user){
//        User user = userService.getUserForDev(1L);
        return nightProfileService.getProfileCardTabList(user, profileId, lastItemId, size);

    }

    @GetMapping("/auction/list")
    public ResultTemplate getProfileAuctionList(@RequestParam(value = "lastItemId", required = false)
                                                       Long lastItemId, @RequestParam("size") int size,
                                                @UserInfo User user){

//        User user = userService.getUserForDev(1L);

        return nightProfileService.getProfileAuctionOnSaleList(user, lastItemId, size);
    }

    @GetMapping("/auction/participation/list")
    public ResultTemplate getProfileParticipatedAuctionList(@RequestParam(value = "lastItemId", required = false)
                                                            Long lastItemId, @RequestParam("size") int size,
                                                            @UserInfo User user){

        return nightProfileService.getProfileParticipatedAuctionList(user, lastItemId, size);

    }






}


