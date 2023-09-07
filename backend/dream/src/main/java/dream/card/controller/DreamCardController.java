package dream.card.controller;


import dream.card.service.DreamCardService;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/night")
@RequiredArgsConstructor
public class DreamCardController {

    private final DreamCardService dreamCardService;


    @GetMapping(value = "/")
    public ResultTemplate getNightMain(){
        return dreamCardService.getNightMain();
    }
}
