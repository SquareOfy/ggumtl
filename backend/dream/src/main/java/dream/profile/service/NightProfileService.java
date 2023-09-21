package dream.profile.service;

import dream.card.domain.DreamCardRepository;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BadRequestException;
import dream.common.exception.NoSuchElementException;
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
public class NightProfileService {


    private  final FollowRepository followRepository;
    private final DreamCardRepository dreamCardRepository;
    private final UserRepository userRepository;

    private final ProfileService profileService;

    public ResultTemplate getNightHeader(User user, Long profileUserId){

        User profileUser = userRepository.findByUserId(profileUserId).orElseThrow(()->{
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });



        int followingCount = followRepository.findByFromId(profileUser.getUserId()).size();
        int  followerCount = followRepository.findByToId(profileUser.getUserId()).size();

        //내 프로필 헤더 조회
        if(user.getUserId()==profileUserId){
           return profileService.getHeaderBySelf(profileUser);

        }else{
            int dreamCardCount = dreamCardRepository.findByDreamCardOwnerId(profileUserId).size();
            ResponseNightProfileHeaderByOther response = ResponseNightProfileHeaderByOther.from(profileUser, dreamCardCount, followerCount, followingCount);
            return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        }
    }




}
