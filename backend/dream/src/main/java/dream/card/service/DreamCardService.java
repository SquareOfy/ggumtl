package dream.card.service;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardQueryRepository;
import dream.card.domain.DreamCardRepository;
import dream.card.domain.WriggleReview;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.dto.response.*;
import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.DeleteException;
import dream.common.exception.NotFoundException;
import dream.common.exception.NotMatchException;
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

    private final DreamCardRepository dreamCardRepository;
    private final DreamCardQueryRepository dreamCardQueryRepository;
    private final UserRepository userRepository;

    /**
     * 밤 메인 화면에서 카드 리스트를 조회 하는 함수 !
     */
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

    // 카드 상세 정보 가져오는 함수
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


    // 카드 조회수 업데이트하는 함수
    @Transactional
    public ResultTemplate updateDreamCard(Long dreamCardId) {

        DreamCard findCard = dreamCardRepository.findOwnerById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        log.info("{}", findCard.getDreamCardOwner().getUserId());
        if (!Objects.equals(findCard.getDreamCardOwner().getUserId(), 1L)) findCard.updateHits();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 카드 좋아요 홤수
    @Transactional
    public ResultTemplate updateCardLike(Long userId, Long dreamCardId) {

        DreamCard dreamCard = dreamCardRepository.findLikeById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        dreamCard.addDreamCardLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 카드 좋아요 취소 함수
    @Transactional
    public ResultTemplate updateCardUnlike(Long userId, Long dreamCardId) {

        DreamCard dreamCard = dreamCardRepository.findLikeById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        dreamCard.deleteDreamCardLike(user);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 카드 전처리 후 결과 반환
    public ResultTemplate getPreProcessingForDreamCard(String dreamCardContent) {

        ResponseDreamCardPreprocessing response = new ResponseDreamCardPreprocessing();
        // 아마 이건 하둡이랑 연관된 함수

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 실제 꿈카드 생성 함수
    public ResultTemplate postDreamCard(RequestDreamCardDetail request) {

        ResponseDreamCardId response = new ResponseDreamCardId();

        // 꿈 카드 삽입하고 PK 반환받기

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
    public ResultTemplate updateCardIsShow(RequestDreamCardIsShow request) {

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 검색창에서 해몽 검색했을때 해몽결과 찾아주는 함수
    // 여기서 아마 해몽 결과 찾는 알고리즘, HDFS에서 데이터 꺼내야할꺼임
    public ResultTemplate getInterpretationResult(String keyword) {

        ResponseInterpretationResult response = new ResponseInterpretationResult();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

}
