package dream.card.service;

import dream.card.domain.*;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.dto.request.RequestKeyword;
import dream.card.dto.response.*;
import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.DeleteException;
import dream.common.exception.NotFoundException;
import dream.common.exception.NotMatchException;
import dream.security.jwt.domain.UserInfo;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DreamCardService {

    private final UserRepository userRepository;
    private final DreamCardRepository dreamCardRepository;
    private final DreamAnalysisService dreamAnalysisService;
    private final DreamKeywordRepository dreamKeywordRepository;
    private final DreamCardQueryRepository dreamCardQueryRepository;

    public ResultTemplate getNightMain(Long lastItemId, int size) {

        List<DreamCard> findCards = dreamCardQueryRepository.findDreamCardPaging(lastItemId, size);
        if (findCards.isEmpty()) throw new NotFoundException(NotFoundException.CARD_LIST_NOT_FOUND);

        List<ResponseDreamCard> dreamCards = findCards.stream()
                .limit(size)
                .map(findCard -> {
                    boolean isLike = findCard.getDreamCardLikes().stream()
                            .anyMatch(dreamCardLike ->
                                    dreamCardLike.getDreamCard().getDreamCardId().equals(findCard.getDreamCardId()) &&
                                            dreamCardLike.getUser().getUserId().equals(1L));
                    return ResponseDreamCard.from(findCard, isLike);
                })
                .collect(Collectors.toList());

        boolean hasNext = findCards.size() > size;
        ResponseDreamCardList list = ResponseDreamCardList.from(dreamCards, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(list).build();
    }

    public ResultTemplate getFlipDreamCardDetail(Long id) {

        DreamCard findCard = dreamCardRepository.findDetailsById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));


        ResponseFlipDreamCardDetail response = ResponseFlipDreamCardDetail.from(findCard);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getDreamCardUserInfo(Long id) {

        DreamCard dreamCard = dreamCardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        ResponseDreamCardUserInfo response = ResponseDreamCardUserInfo.from(dreamCard);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }


    @Transactional
    public ResultTemplate updateDreamCard(Long dreamCardId) {

        DreamCard findCard = dreamCardRepository.findOwnerById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        findCard.updateHits();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate updateCardLike(Long userId, Long dreamCardId) {

        DreamCard dreamCard = dreamCardRepository.findLikeById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        dreamCard.addDreamCardLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate updateCardUnlike(Long userId, Long dreamCardId) {

        DreamCard dreamCard = dreamCardRepository.findLikeById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        dreamCard.deleteDreamCardLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public ResultTemplate getPreProcessingForDreamCard(String dreamCardContent) {

        // 아마 이건 하둡이랑 연관된 함수
        // dreamCardContent 를 통해, keyword를 추출
        // 해몽 내용도 조회
        // 추출하는 과정에서 희귀도와 길몽도를 판단 -> 등급까지 설정
        // 종합해서 전체 등급 설정
        // ResponseDreamCardPreprocessing 안에 넣어서 응답 !
        ResponseDreamCardPreprocessing response = ResponseDreamCardPreprocessing.from();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @Transactional
    public ResultTemplate postDreamCard(User author, RequestDreamCardDetail request, String fileName) {

        List<DreamKeyword> keywords = dreamKeywordRepository.findByKeywordIn(request.getKeywords());

        ResponseDreamAnalysis responseDreamAnalysis = dreamAnalysisService.processAnalysis(request);

        List<String> wordKeywords = request.getWordKeywords();
        // 키워드 기반으로 희귀도 판단하는 녀석 하나 만들기

        // 희귀도 기반으로 등급 추출

        // 긍정도 등급이랑 희귀도 기반으로 꿈 최종 등급 추출

        // 꿈 해몽 내용 추출

        // 얘네를 넣을 DTO
//        dreamCard.rarePoint = 23;
//        dreamCard.grade = Grade.SS;
//        dreamCard.dreamTelling = "아직 못 했어요 구현을";
//        dreamCard.positiveGrade = Grade.S;
//        dreamCard.rareGrade = Grade.SS;

        DreamCard makeDreamCard = DreamCard.makeDreamCard(request, author, keywords, fileName, responseDreamAnalysis);
        dreamCardRepository.save(makeDreamCard);
        ResponseDreamCardId response = ResponseDreamCardId.from(makeDreamCard);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    public ResultTemplate getDreamCardImage(Long dreamCardId) {

        DreamCard dreamCard = dreamCardRepository.findLikeById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        ResponseDreamCardImage response = ResponseDreamCardImage.from(dreamCard);
        
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 카드 소유자가 보는 카드 디테일 정보 가져오기
    public ResultTemplate getDreamCardDetailByUser(Long id, Long userId) {

        DreamCard findCard = dreamCardRepository.findDetailsLikeById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        if (!findCard.getDreamCardOwner().getUserId().equals(userId))
            throw new NotMatchException(NotMatchException.CARD_OWNER_MATCH);

        boolean isTrue = findCard.getWriggleReviews().stream()
                .anyMatch(review -> review.getBuyerId().getUserId().equals(userId));

        BaseCheckType reviewStatus = isTrue ? BaseCheckType.T : BaseCheckType.F;
        ResponseDreamCardDetailByUser response = ResponseDreamCardDetailByUser.from(findCard, reviewStatus);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 꿈 카드 삭제하기 함수
    @Transactional
    public ResultTemplate deleteDreamCard(Long id, Long userId) {

        DreamCard findCard = dreamCardRepository.findDetailsLikeById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        if (findCard.getAuctionStatus().equals(BaseCheckType.T)) throw new DeleteException(DeleteException.DELETE_CARD_STATUS);
        if (!findCard.getDreamCardOwner().getUserId().equals(userId)) throw new DeleteException(DeleteException.DELETE_CARD_OWNER);

        dreamCardRepository.delete(findCard);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 꿈 카드 공개여부 업데이트 함수
    @Transactional
    public ResultTemplate updateCardIsShow(RequestDreamCardIsShow request, Long userId) {

        DreamCard findCard = dreamCardRepository.findById(request.getDreamCardId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        if (!findCard.getDreamCardOwner().getUserId().equals(userId)) throw new NotMatchException(NotMatchException.CARD_OWNER_MATCH);

        boolean isShow = findCard.getIsShow().equals(BaseCheckType.T);
        findCard.updateIsShow(isShow);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 검색창에서 해몽 검색했을때 해몽결과 찾아주는 함수
    // 여기서 아마 해몽 결과 찾는 알고리즘, HDFS에서 데이터 꺼내야할꺼임
    public ResultTemplate getInterpretationResult(String keyword) {

        ResponseInterpretationResult response = new ResponseInterpretationResult();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }
}
