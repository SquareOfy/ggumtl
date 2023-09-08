package dream.card.service;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardLike;
import dream.card.domain.DreamCardRepository;
import dream.card.dto.response.ResponseDreamCardList;
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

        List<DreamCard> findCards = dreamCardRepository.findFetchTestByAll()
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_LIST_NOT_FOUND));

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
}
