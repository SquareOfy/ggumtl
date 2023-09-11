package dream.card.service;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.card.domain.DreamCardRepository;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.dto.response.*;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DreamCardService {

    private final DreamCardRepository dreamCardRepository;

    /**
     *
     * 밤 메인 화면에서 카드 리스트를 조회 하는 함수 !
     */
    public ResultTemplate getNightMain(){

        List<DreamCard> findCards = dreamCardRepository.findCardInfoByAll();
        if (findCards.isEmpty()) throw new NotFoundException(NotFoundException.CARD_LIST_NOT_FOUND);

        List<ResponseDreamCardList> list = new ArrayList<>();
        for (DreamCard findCard : findCards) {
//            boolean isLike = dreamCardRepository.existLikeCardByUser(findCard.getDreamCardId(), 1);
            List<DreamCardLike> dreamCardLikes = findCard.getDreamCardLike();
            boolean isLike = false;
            for (DreamCardLike dreamCardLike : dreamCardLikes) {
                if (dreamCardLike.getDreamCard().getDreamCardId().equals(findCard.getDreamCardId()) &&
                    dreamCardLike.getUser().getUserId().equals(1L)) {
                    isLike = true;
                    break;
                }
            }
            list.add(ResponseDreamCardList.from(findCard, isLike));
        }

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(list).build();
    }

    // 카드 상세 정보 가져오는 함수
    public ResultTemplate getFlipDreamCardDetail(long id) {

        ResponseFlipDreamCardDetail response = new ResponseFlipDreamCardDetail();
        
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 카드 조회수 업데이트하는 함수
    public ResultTemplate updateDreamCard(long dreamCardId) {

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 카드 좋아요 홤수
    public ResultTemplate updateCardLike(long dreamCardId){
        // 매개변수에 userId 추가
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 카드 좋아요 취소 함수
    public ResultTemplate updateCardUnlike(long dreamCardId){
        // 매개변수에 userId 추가
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
    public ResultTemplate getDreamCardDetailByUser(long dreamCardId) {

        ResponseDreamCardDetailByUser response = new ResponseDreamCardDetailByUser();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 꿈 카드 삭제하기 함수
    public ResultTemplate deleteDreamCard(long dreamCardId) {

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
