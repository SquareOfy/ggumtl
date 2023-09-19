package dream.user.controller;

import dream.common.domain.ResultTemplate;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.dto.request.RequestToId;
import dream.user.dto.request.RequestNickname;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController{

    private final UserService userService;

    // 예시 - 지워질 코드
    @GetMapping("/{id}")
    public ResultTemplate getUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }

    
    //테스트 코드
    @GetMapping("/jwt-test")
    public ResultTemplate testJwt(@UserInfo User user) {
        log.info("userId : {} ", user.getUserId());
        log.info("userEmail : {} ", user.getEmail());

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(user).build();
    }

    @PostMapping("/logout")
    public ResultTemplate logout(@UserInfo User user, HttpServletRequest request){

        return userService.logout(user,request );

    }

//    @PostMapping("/refresh-token")


    @PutMapping("signup/extra-info")
    public ResultTemplate setExtraInfo(HttpServletResponse response, @UserInfo User user, @RequestBody RequestNickname request){
        log.info("nickname : {}", request);
        return userService.setNickname(response, user, request);
    }

    @PostMapping("/refresh-token")
    public ResultTemplate reissueRefreshToken(){

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @GetMapping("/nickname/duplication/{nickname}")
    public ResultTemplate checkDuplicationNick(@PathVariable RequestNickname nickname){
        return userService.checkDuplicateNick(nickname);
    }

    @PutMapping("/nickname")
    public ResultTemplate updateNickname(@UserInfo User user, @RequestBody RequestNickname nickname){
        return userService.updateNickname(user, nickname);
    }

    @PostMapping("/follow")
    public ResultTemplate postFollow(@UserInfo User user, @RequestBody RequestToId request){
        return userService.follow(user, request);
    }

    @DeleteMapping("/unfollow/{toId}")
    public ResultTemplate deleteFollow(@UserInfo User user, @PathVariable Long toId){
        return userService.unfollowToId(user, toId);
    }


}
