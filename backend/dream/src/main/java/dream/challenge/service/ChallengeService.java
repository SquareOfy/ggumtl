package dream.challenge.service;


import dream.card.domain.DreamKeyword;
import dream.card.domain.DreamKeywordRepository;
import dream.challenge.domain.*;
import dream.challenge.dto.response.*;
import dream.challenge.dto.request.RequestTimeCapsule;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NoSuchElementException;
import dream.common.exception.NotFoundException;
import dream.common.exception.DuplicateException;
import dream.s3.dto.response.ResponseBadgeImage;
import dream.user.domain.FollowRepository;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeQueryRepository challengeQueryRepository;
    private final DreamKeywordRepository dreamKeywordRepository;
    private final ChallengeDetailQueryRepository challengeDetailQueryRepository;
    private final ChallengeParticipationRepository challengeParticipationRepository;


    /**
     * 낮 메인 화면 조회 !@!
     */
    public ResultTemplate getDayMain(Long keywordId, Long lastItemId, int size) {

        List<Challenge> challenges = challengeQueryRepository.findChallengeListByPage(keywordId, lastItemId, size);
        if (challenges.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_CHALLENGE_LIST);

        List<ResponseChallenge> responseChallengeList = new ArrayList<>();
        boolean hasNext = challenges.size() > size;

        int count = 0;
        for (Challenge challenge : challenges) {
            responseChallengeList.add(ResponseChallenge.from(challenge));

            if (++count == size) break;
        }
        ResponseChallengeList response = ResponseChallengeList.from(responseChallengeList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getAllCategory() {

        List<DreamKeyword> keywords = dreamKeywordRepository.findAll();
        if(keywords.isEmpty()) throw new NotFoundException(NotFoundException.DREAM_KEYWORD_NOT_FOUND);

        List<ResponseKeyword> response = new ArrayList<>();
        for(DreamKeyword keyword: keywords){
            response.add(ResponseKeyword.from(keyword));
        }

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getFollowUsers(User user, Long lastItemId, int size) {

        List<ChallengeDetail> list = challengeDetailQueryRepository.findChallengeListByPage(user.getUserId(), lastItemId, size);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_FOLLOWING_USER_STORY);

        List<ResponseChallengeDetailIdWithNameAndNickName> userList = new ArrayList<>();
        int count = 0;
        for (ChallengeDetail challengeDetail : list) {
            ResponseChallengeDetailIdWithNameAndNickName nickAndId = ResponseChallengeDetailIdWithNameAndNickName.from(challengeDetail);
            userList.add(nickAndId);
            if(++count == size) break;
        }

        boolean hasNext = (list.size() > size);
        ResponseFollowingUsers response = ResponseFollowingUsers.from(true, userList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getFollowUserStory(long userId) {
        List<ChallengeDetail> list = challengeDetailQueryRepository.getStoryByUserId(userId);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_FOLLOWING_USER_STORY);

        List<ResponseFollowingUserStory> response = new ArrayList<>();
        for (ChallengeDetail challengeDetail : list) {
            response.add(ResponseFollowingUserStory.from(challengeDetail));
        }

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getSearchedChallenge(String searchKeyword, Long keywordId, Long lastItemId, int size) {

        List<Challenge> list = challengeQueryRepository.getChallengeByKeyword(searchKeyword, keywordId, lastItemId, size);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_CHALLENGE_LIST);

        List<ResponseSearchedChallenge> challengeList = new ArrayList<>();
        int count = 0;
        for (Challenge challenge : list) {
            ResponseSearchedChallenge tmp = ResponseSearchedChallenge.from(challenge);
            challengeList.add(tmp);
            if(++count == size) break;
        }

        boolean hasNext = (list.size() > size);
        ResponseSearchedChallengeList response = ResponseSearchedChallengeList.from(challengeList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getChallengeInfo(User user, Long challengeId) {

        List<ChallengeDetail> sizeOfUserParticipateInChallenge = challengeDetailQueryRepository.
                getIsUserParticipateChallenge(user.getUserId(), challengeId);

        Challenge challengeWithKeyword = challengeRepository.findChallengeKeyword(challengeId)
                .orElseThrow( () ->  new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        Challenge challengeWithParticipates = challengeRepository.findChallengeParticipates(challengeId)
                .orElseThrow( () ->  new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        List<User> getRank = challengeDetailQueryRepository.getRank(challengeId);

        ResponseChallengeInfo response = ResponseChallengeInfo
                .from(sizeOfUserParticipateInChallenge, challengeWithKeyword, challengeWithParticipates, getRank);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getChallengeImage(Long challengeId) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow( () ->  new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        ResponseBadgeImage response = ResponseBadgeImage.from(challenge);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @Transactional
    public ResultTemplate postParicipateChallenge(Long userId, Long challengeId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        Optional<ChallengeParticipation> list = challengeParticipationRepository.getChallengeParticipationListByUserAndChallenge(userId, challengeId);

        boolean isDuplicated = list.stream()
                .anyMatch(participation -> participation.getIsIn().equals(ChallengeStatus.P));

        if(isDuplicated) throw new DuplicateException(DuplicateException.CHALLENGE_PARTICIPATION_DUPLICATE);

        ChallengeParticipation challengeParticipation = ChallengeParticipation.createChallengeParticipation(user, challenge);
        challengeParticipationRepository.save(challengeParticipation);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }


    @Transactional
    public ResultTemplate updateChallengeHits(Long challengeId) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow( () ->  new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        challenge.updateChallengeHits();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate postTimeCapsule(User user, RequestTimeCapsule request) {

        Optional<ChallengeParticipation> list = challengeParticipationRepository
                .getChallengeParticipationListByUserAndChallengeAndStatus(user.getUserId(), request.getChallengeId(), ChallengeStatus.P);

        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_CHALLNENGE_PARTICIPATE);

        ChallengeParticipation challengeParticipation = list.get();
        challengeParticipation.updateTimeCapsuleContent(request.getTimeCapsuleContent());

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public ResultTemplate writeDetailPossible(User user, Long challengeId) {

        List<ChallengeDetail> writeDetailPossibleList = challengeDetailQueryRepository.
                getChallengeDetailByUserIdAndChallengeIdAndDate(user.getUserId(), challengeId);
        
        // 이 챌린지에 참여중인지 어떻게 알지 예외 처리가 필요할 수 있겠다.

        if(writeDetailPossibleList.size() == 1) throw new DuplicateException(DuplicateException.CHLLENGE_DETAIL_DATE_DUPLICATE);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }
}
