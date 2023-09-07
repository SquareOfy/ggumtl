package dream.card.service;

import dream.card.domain.DreamCardRepository;
import dream.card.dto.response.RequestDreamCardDto;
import dream.common.domain.ResultTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DreamCardService {

    private final DreamCardRepository dreamCardRepository;

    public ResultTemplate getNightMain(){


        // 디비에서 값 가져오고
        List<RequestDreamCardDto> requestDreamCardDtoList = new ArrayList<>();

        //    dreamCardid : 22222,
        //    dreamCardOwner : 12345,
        //    dreamCardAuthor : 67891,
        //    createdAt : "2023/09/06",



        //    ownerNickname : "yy",
        //    ownerProfileUrl : "{profileUrl}"

        //    likedNumber : 120,
        //    isLike : true

        return null;
    }
}
