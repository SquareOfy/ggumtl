package dream.s3.controller;

import dream.challenge.service.ChallengeService;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/s3")
public class S3Controller {

    private final ChallengeService challengeService;

    @GetMapping(value = "/day/challenge/item/{challengeId}/image")
    public ResultTemplate getChallengeImage(@PathVariable("challengeId") Long challengeId) {

        return challengeService.getChallengeImage(challengeId);
    }
}

