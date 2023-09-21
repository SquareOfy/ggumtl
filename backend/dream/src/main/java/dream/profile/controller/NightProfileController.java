package dream.profile.controller;

import dream.common.domain.ResultTemplate;
import dream.profile.service.NightProfileService;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/night")
public class NightProfileController {

    private final UserService userService;

    private final NightProfileService nightProfileService;




    //개발용
    @GetMapping("/header/{profileUserId}")
    public ResultTemplate getNightHeader( @PathVariable Long profileUserId){
        User user = userService.getUserForDev(20L);

        return nightProfileService.getNightHeader(user, profileUserId);
    }


    //배포용
//    @GetMapping("/header/{profileUserId}")
//    public ResultTemplate getNightHeader(@UserInfo User user, @PathVariable Long profileUserId){
//        return nightProfileService.getNightHeader(user, profileUserId);
//    }


}


