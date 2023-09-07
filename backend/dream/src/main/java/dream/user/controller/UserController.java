package dream.user.controller;

import dream.common.domain.ResultTemplate;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{

    private final UserService userService;

    // 예시 - 지워질 코드
    @GetMapping("/{id}")
    public ResultTemplate getUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }
}
