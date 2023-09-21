package dream.profile.controller;

import dream.common.domain.ResultTemplate;
import dream.profile.service.DayProfileService;
import dream.user.domain.User;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile/day")
public class DayProfileController {


    private final UserService userService;
    private final DayProfileService dayProfileService;


    @GetMapping("/header/{profileUserId}")
    public ResultTemplate getNightHeader(@PathVariable Long profileUserId){
        User user = userService.getUserForDev(20L);

        return dayProfileService.getDayHeader(user, profileUserId);
    }
}
