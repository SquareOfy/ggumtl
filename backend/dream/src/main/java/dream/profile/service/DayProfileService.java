package dream.profile.service;

import dream.card.domain.DreamCardRepository;
import dream.challenge.domain.ChallengeParticipationRepository;
import dream.challenge.domain.ChallengeRepository;
import dream.challenge.domain.ChallengeStatus;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BadRequestException;
import dream.profile.dto.response.ResponseNightProfileHeaderByOther;
import dream.profile.dto.response.ResponseProfileHeaderBySelf;
import dream.user.domain.FollowRepository;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DayProfileService {
    private  final FollowRepository followRepository;
    private final DreamCardRepository dreamCardRepository;
    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final ChallengeParticipationRepository challengeParticipationRepository;
    public ResultTemplate getDayHeader(User user, Long profileUserId) {

        User profileUser = userRepository.findByUserId(profileUserId).orElseThrow(()->{
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });


        int followingCount = followRepository.findByFromId(profileUser.getUserId()).size();
        int followerCount = followRepository.findByToId(profileUser.getUserId()).size();

        //내 프로필 헤더 조회
        if(user.getUserId()==profileUserId){
           return profileService.getHeaderBySelf(profileUser);
        }else{

            log.info("userId : {} ", user.getUserId());
            log.info("List : {} ", challengeParticipationRepository.getChallengeParticipationListByUserAndStatus(profileUser.getUserId(), ChallengeStatus.S));
            int finishedChallengeCount  =  challengeParticipationRepository.getChallengeParticipationListByUserAndStatus(profileUser.getUserId(), ChallengeStatus.S).size();
            ResponseNightProfileHeaderByOther response = ResponseNightProfileHeaderByOther.from(profileUser, finishedChallengeCount, followerCount, followingCount);
            return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        }

    }
}
