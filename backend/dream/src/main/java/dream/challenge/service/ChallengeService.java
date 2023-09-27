package dream.challenge.service;


import dream.card.domain.DreamKeyword;
import dream.card.domain.DreamKeywordRepository;
import dream.challenge.domain.*;
import dream.challenge.dto.request.RequestChallenge;
import dream.challenge.dto.request.RequestComment;
import dream.challenge.dto.response.*;
import dream.challenge.dto.request.RequestTimeCapsule;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BadRequestException;
import dream.common.exception.NoSuchElementException;
import dream.common.exception.NotFoundException;
import dream.common.exception.DuplicateException;
import dream.s3.dto.request.RequestChallengeDetail;
import dream.s3.dto.response.ResponseBadgeImage;
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
    private final commentRepository commentRepository;
    private final ChallengeRepository challengeRepository;
    private final DreamKeywordRepository dreamKeywordRepository;
    private final commentQueryRepository commentQueryRepository;
    private final ChallengeQueryRepository challengeQueryRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    private final ChallengeKeywordRepository challengeKeywordRepository;
    private final ChallengeDetailQueryRepository challengeDetailQueryRepository;
    private final ChallengeParticipationRepository challengeParticipationRepository;

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

    @Transactional
    public ResultTemplate postChallengeDetail(User user, RequestChallengeDetail requestChallengeDetail, String fileName) {

        Challenge challenge = challengeRepository.findById(requestChallengeDetail.getChallengeId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        ChallengeDetail challengeDetail = ChallengeDetail.makeChallengeDetail(user, requestChallengeDetail, challenge, fileName);
        challengeDetailRepository.save(challengeDetail);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public Long postChallenge(User requestUser, RequestChallenge request) {

        User user = userRepository.findById(requestUser.getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Challenge challenge = Challenge.makeChallenge(user, request);
        challengeRepository.save(challenge);

        return challenge.getChallengeId();
    }

    @Transactional
    public ResultTemplate postChallengeKeyword(Long challengeId, RequestChallenge request) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        DreamKeyword dreamKeyword = dreamKeywordRepository.findById(request.getKeywordId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.DREAM_KEYWORD_NOT_FOUND));

        ChallengeKeyword challengeKeyword = ChallengeKeyword.makeChallengeKeyword(challenge, dreamKeyword);
        challengeKeywordRepository.save(challengeKeyword);

        ResponseChallengeId response = new ResponseChallengeId(challengeId);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getComments(Long detailId, Long lastItemId, int size) {

        List<comment> list = commentQueryRepository.findCommentByPage(detailId, lastItemId, size);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_COMMENT);

        List<ResponseComment> commentList = new ArrayList<>();
        int count = 0;
        for(comment comment : list){
            ResponseComment responseComment = ResponseComment.from(comment);
            commentList.add(responseComment);
            if(++count == size) break;
        }

        boolean hasNext = (list.size() > size);
        ResponseCommentsssss response = ResponseCommentsssss.from(commentList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @Transactional
    public ResultTemplate postComment(User user, RequestComment request) {

        ChallengeDetail challengeDetail = challengeDetailRepository.findById(request.getDetailId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_DETAIL_NOT_FOUND));

        comment postComment = comment.makeComment(user, request, challengeDetail);
        commentRepository.save(postComment);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate deleteComment(Long commentId) {

        comment dComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.COMMENT_NOT_FOUND));
        commentRepository.delete(dComment);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate postLike(User user, Long challengeDetailId) {

        ChallengeDetail challengeDetail = challengeDetailRepository.findById(challengeDetailId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_DETAIL_NOT_FOUND));

        challengeDetail.addChallengeDetailLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate postUnLike(User user, Long challengeDetailId) {

        ChallengeDetail challengeDetail = challengeDetailRepository.findById(challengeDetailId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_DETAIL_NOT_FOUND));

        challengeDetail.deleteChallengeDetailLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public ResultTemplate getChallengeDetails(User user, Long challengeId, Long lastItemId, int size) {

        List<ChallengeDetail> list = challengeDetailQueryRepository.getChallengeDetailByChallengeId(challengeId, lastItemId, size);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_CHALLENGE_DETAIL);

        List<ResponseChallengeDetail> challengeDetailList = new ArrayList<>();
        int count = 0;
        for (ChallengeDetail challengeDetail : list) {
            boolean isLike = challengeDetail.getChallengeDetailLikes().stream()
                    .anyMatch(like -> like.getUser().getUserId().equals(user.getUserId()));

            List<ChallengeDetail> forCountList = challengeDetailQueryRepository
                    .getChallengeDetailByChallengeIdAndUserId(challengeId, challengeDetail.getUser().getUserId());

            ResponseChallengeDetail tmp = ResponseChallengeDetail.from(challengeDetail, isLike, forCountList.size());
            // challengeDetailCount 처리
            challengeDetailList.add(tmp);
            if(++count == size) break;
        }

        boolean hasNext = (list.size() > size);
        ResponseChallengeDetailResult response = ResponseChallengeDetailResult.from(challengeDetailList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getMyChallengeList(User user, Long lastItemId, int size) {

        List<Challenge> list = challengeQueryRepository.getChallengeByUserId(user.getUserId(), lastItemId, size);
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_CHALLENGE_LIST);

        List<ResponseMyChallengeInfo> resultList = new ArrayList<>();
        int count = 0;
        for(Challenge challenge : list){
            ResponseMyChallengeInfo tmp = ResponseMyChallengeInfo.from(challenge);
            resultList.add(tmp);
            if(++count == size) break;
        }

        boolean hasNext = (list.size() > size);
        ResponseMyChallengeInfoResult response = ResponseMyChallengeInfoResult.from(resultList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getMyChallengeInfo(User user, Long challengeMidId) {

        Challenge challenge = challengeRepository.findById(challengeMidId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        List<ChallengeDetail> challengeDetailList = challengeDetailQueryRepository
                .getOneChallengeDetailByUserIdAndChallengeIdAndDate(user.getUserId(), challengeMidId);

        boolean canWrite = challengeDetailList.isEmpty();

        ResponseMyChallengeInfoDetail response = ResponseMyChallengeInfoDetail.from(challenge, canWrite);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getTimeCapsule(User user, Long challengeId, Long lastItemId, int size) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CHALLENGE_NOT_FOUND));

        long detailCount = challenge.getChallengeDetails().stream()
                .filter(challengeDetail -> challengeDetail.getUser().getUserId().equals(user.getUserId()))
                .count();

        if(detailCount < challenge.getTimeCapsuleOpenAt())
            throw new BadRequestException(BadRequestException.CANNOT_READ_TIMECAPSULE);

        List<ChallengeParticipation> list = challenge.getChallengeParticipations();
        if(list.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_TIMECAPSULE);

        ArrayList<ResponseTimeCapsule> timeCapsules = new ArrayList<>();

        int count = 0;
        boolean flag = lastItemId != null;
        if(flag){
            for(ChallengeParticipation challengeParticipation : list){
                if (challengeParticipation.getChallengeParticipationId() > lastItemId && !challengeParticipation.getTimeCapsuleContent().equals(" ")) {
                    ResponseTimeCapsule tmp = ResponseTimeCapsule.from(challengeParticipation);
                    timeCapsules.add(tmp);
                    if(++count == size) break;
                }
            }
        }
        else{
            for(ChallengeParticipation challengeParticipation : list){
                if (!challengeParticipation.getTimeCapsuleContent().equals(" ")) {
                    ResponseTimeCapsule tmp = ResponseTimeCapsule.from(challengeParticipation);
                    timeCapsules.add(tmp);
                    if(++count == size) break;
                }
            }
        }


        boolean hasNext = (list.size() > size);
        ResponseTimeCapsuleResult response = ResponseTimeCapsuleResult.from(challenge, timeCapsules, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }
}
