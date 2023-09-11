package dream.challenge.service;


import dream.challenge.domain.Challenge;
import dream.challenge.domain.ChallengeQueryRepository;
import dream.challenge.domain.ChallengeRepository;
import dream.challenge.dto.response.ResponseChallenge;
import dream.challenge.dto.response.ResponseChallengeList;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeQueryRepository challengeQueryRepository;

    /**
     * 낮 메인 화면 조회 !@!
     */
    public ResultTemplate getDayMain(Long keywordId, Long lastItemId, int size) {

        List<Challenge> challenges = challengeQueryRepository.findChallengeListByPage(keywordId, lastItemId, size);
        if (challenges.isEmpty()) throw new NotFoundException(NotFoundException.CHALLENGE_LIST_NOT_FOUND);

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
}
