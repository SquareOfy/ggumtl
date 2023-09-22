package dream.s3.controller;

import dream.challenge.domain.Challenge;
import dream.challenge.domain.ChallengeRepository;
import dream.challenge.service.ChallengeService;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import dream.s3.AwsS3Uploader;
import dream.s3.dto.request.RequestChallengeDetail;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/s3")
public class S3Controller {

    private final UserService userService;
    private final AwsS3Uploader awsS3Uploader;
    private final UserRepository userRepository;
    private final ChallengeService challengeService;
    private final ChallengeRepository challengeRepository;

    @GetMapping(value = "/day/challenge/item/{challengeId}/image")
    public ResultTemplate getChallengeImage(@PathVariable("challengeId") Long challengeId) {

        return challengeService.getChallengeImage(challengeId);
    }


//    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
//        String fileName = awsS3Uploader.upload(multipartFile, "test");
//        log.info("for upload file name : {}", fileName);
//        return fileName;
//    }

    @PostMapping("/userprofile")
    public ResultTemplate uploadProfileImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        String fileName = awsS3Uploader.upload(multipartFile, "userProfile");

        log.info("for upload file name : {}", fileName);

        return userService.updateUserImage(user, fileName);
    }

    @GetMapping("/userimage")
    public ResultTemplate getProfileImage(){

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return userService.getUserImage(user);
    }

    @PostMapping("/challenge/detail/new")
    public ResultTemplate uploadChallengeDetail(@RequestPart("challengeDetail") RequestChallengeDetail requestChallengeDetail,
            @RequestPart("file") MultipartFile multipartFile) throws IOException {

        User user = userRepository.findByUserId(2L).
                orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Challenge challenge = challengeRepository.findById(requestChallengeDetail.getChallengeId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        String fileName = awsS3Uploader.upload(multipartFile, "challengeDetail");

        log.info("for upload file name : {}", fileName);

        return challengeService.postChallengeDetail(user, requestChallengeDetail, fileName);
    }
}

