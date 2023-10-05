package dream.profile.service;

import dream.card.domain.DreamCardRepository;
import dream.challenge.domain.ChallengeParticipationQueryRepository;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BadRequestException;
import dream.common.exception.NotFoundException;
import dream.profile.dto.response.*;
import dream.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final FollowQueryRepository followQueryRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final ChallengeParticipationQueryRepository challengeParticipationQueryRepository;
    private final DreamCardRepository dreamCardRepository;

    public ResultTemplate getFollowingList(Long fromId, Long lastFollowId, int size) {
        User user = userRepository.findByUserId(fromId).orElseThrow(() -> {
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });

        List<Follow> findFollowings = followQueryRepository.findFollowByFromId(fromId, lastFollowId, size);

        if (findFollowings.isEmpty()) throw new NotFoundException(NotFoundException.FOLLOWING_NOT_FOUND);

        List<ResponseFollowingUserInfo> followingList = findFollowings.stream()
                .limit(size)
                .map(follow -> {
                    return ResponseFollowingUserInfo.from(follow);
                }).limit(size).collect(Collectors.toList());

        log.info("followgList.size() : {} ", followingList.size());
        boolean hasNext = findFollowings.size() > size;

        ResponseFollowingList response = ResponseFollowingList.from(followingList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();

    }

    public ResultTemplate getFollowerList(Long toId, Long lastFollowId, int size) {

        User user = userRepository.findByUserId(toId).orElseThrow(() -> {
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });

        List<Follow> findFollowers = followQueryRepository.findFollowByToId(toId, lastFollowId, size);

        if (findFollowers.isEmpty()) throw new NotFoundException(NotFoundException.FOLLOWER_NOT_FOUND);

        List<ResponseFollowerUserInfo> followerList = findFollowers.stream()
                .limit(size)
                .map(follow -> {
                    return ResponseFollowerUserInfo.from(follow);
                }).limit(size).collect(Collectors.toList());


        boolean hasNext = findFollowers.size() > size;

        ResponseFollowerList response = ResponseFollowerList.from(followerList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();

    }


    public ResultTemplate updatePoint(User user, int point){
        if(user.getPoint()+point>=0) {
            user.plusPoint(point);
        }else{
            user.minusPoint(user.getPoint());
        }

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }


    public ResultTemplate getHeader(User user, Long profileUserId) {

        User profileUser = userRepository.findByUserId(profileUserId).orElseThrow(()->{
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });


        int followingCount = followRepository.findByFromId(profileUser.getUserId()).size();
        int followerCount = followRepository.findByToId(profileUser.getUserId()).size();
        int finishedChallengeCount  = challengeParticipationQueryRepository.getFinishedChallengeListByUserId(profileUserId).size();
        int dreamCardCount = dreamCardRepository.findByDreamCardOwnerId(profileUserId).size();
        //내 프로필 헤더 조회
        if(user.getUserId()==profileUserId){
            ResponseProfileHeaderBySelf response = ResponseProfileHeaderBySelf.from(profileUser, finishedChallengeCount, followerCount, followingCount, dreamCardCount);

            return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        }else{


//                    challengeParticipationRepository.getChallengeParticipationListByUserAndStatus(profileUser.getUserId(), ChallengeStatus.S).size();
            ResponseProfileHeaderByOther response = ResponseProfileHeaderByOther.from(profileUser, finishedChallengeCount, followerCount, followingCount, dreamCardCount);
            return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        }

    }




}
