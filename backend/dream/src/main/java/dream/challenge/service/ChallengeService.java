package dream.challenge.service;


import dream.card.domain.DreamKeyword;
import dream.card.domain.DreamKeywordRepository;
import dream.challenge.domain.*;
import dream.challenge.dto.response.*;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NoSuchElementException;
import dream.common.exception.NotFoundException;
import dream.user.domain.FollowRepository;
import dream.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeQueryRepository challengeQueryRepository;
    private final DreamKeywordRepository dreamKeywordRepository;
    private final FollowRepository followRepository;
    private final ChallengeDetailQueryRepository challengeDetailQueryRepository;

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
}
