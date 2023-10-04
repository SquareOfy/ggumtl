package dream.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/login")
public class LoginController {
    @GetMapping()
    public String login(){
        return "redirect:/oauth2/authorization/kakao";
    }
}
