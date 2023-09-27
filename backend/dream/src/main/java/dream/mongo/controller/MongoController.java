package dream.mongo.controller;

import dream.common.domain.ResultTemplate;
import dream.mongo.domain.Dream;
import dream.mongo.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mongo")
public class MongoController {

    private final MongoService mongoService;

    @GetMapping(value = "")
    public ResultTemplate mongoTest() {

        List<Dream> response = mongoService.getAllDream();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @GetMapping(value = "/find")
    public ResultTemplate findBest(){

        return mongoService.findBest("비둘기");
    }
    
    @GetMapping(value = "/savetest")
    public ResultTemplate saveTest(){

        List<Dream> dreams = new ArrayList<>();
        
        // 파일 읽어서 꿈 객체로 변환

        

        return mongoService.saveDream(dreams);
    }

//    @GetMapping(value = "/findBestDream")
//    public ResultTemplate findBestDream(@RequestPart Req){
//
//    }
}
