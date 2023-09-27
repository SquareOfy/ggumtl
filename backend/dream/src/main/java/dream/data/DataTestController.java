package dream.data;

import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/datatest")
public class DataTestController {

    @GetMapping("/a")
    public ResultTemplate test(){

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }
}
