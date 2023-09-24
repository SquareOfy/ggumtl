package dream.mongo.controller;

import dream.common.domain.ResultTemplate;
import dream.mongo.domain.dream;
import dream.mongo.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mongo")
public class MongoController {

    private final MongoService mongoService;

    @GetMapping(value = "")
    public ResultTemplate mongoTest() {

        List<dream> response = mongoService.getAllDream();
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @GetMapping(value = "/find")
    public ResultTemplate findBest(){

        return mongoService.findBest("비둘기");
    }
}
