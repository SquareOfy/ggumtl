package dream.user.controller;

import dream.common.domain.ResultTemplate;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.dto.request.RequestNickname;
import dream.user.dto.response.ResponseUser;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/jwt-test")
    public ResultTemplate testJwt(@UserInfo User user) {
        log.info("User : {} ", user.toString());
        ResponseUser responseUser = ResponseUser.from(user);
        ResultTemplate resultTemplate = ResultTemplate.builder().status(HttpServletResponse.SC_OK).data(responseUser).build();

        return resultTemplate;
    }

    @PutMapping("signup/extra-info")
    public ResultTemplate setExtraInfo(HttpServletResponse response, @UserInfo User user, @RequestBody RequestNickname request){

        return userService.setNickname(response, user, request);
    }
    @PostMapping("/logout")
    public ResultTemplate logout(@UserInfo User user, HttpServletRequest request){

        return userService.logout(user,request );

    }
    @GetMapping("/nickname/duplication/{nickname}")
    public ResultTemplate checkDuplicationNick(@PathVariable RequestNickname nickname){
        return userService.checkDuplicateNick(nickname);
    }
    @PutMapping("/nickname")
    public ResultTemplate updateNickname(@UserInfo User user, @RequestBody RequestNickname nickname){
        return userService.updateNickname(user, nickname);
    }




}
