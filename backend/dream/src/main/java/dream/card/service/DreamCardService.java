package dream.card.service;

import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardRepository;
import dream.card.dto.response.ResponseDreamCard;
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

    public ResultTemplate getNightMain(){

        List<DreamCard> findCards = dreamCardRepository.findFetchTestByAll()
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        List<ResponseDreamCard> list = new ArrayList<>();
        for (DreamCard findCard : findCards) {
            list.add(ResponseDreamCard.from(findCard));

            log.info("{}",findCard.getDreamCardLike().size());
        }

        return ResultTemplate.builder().status(HttpStatus.OK).data(list).build();
    }
}
