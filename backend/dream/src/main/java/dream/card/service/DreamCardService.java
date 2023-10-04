package dream.card.service;

import dream.auction.domain.Auction;
import dream.auction.domain.AuctionRepository;
import dream.card.domain.*;
import dream.card.dto.request.RequestDreamCardDetail;
import dream.card.dto.request.RequestDreamCardIsShow;
import dream.card.dto.response.*;
import dream.challenge.domain.Challenge;
import dream.challenge.domain.ChallengeRepository;
import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.DeleteException;
import dream.common.exception.NotFoundException;
import dream.common.exception.NotMatchException;
import dream.mongo.domain.Dream;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    private final AuctionRepository auctionRepository;
    private final DreamCardQueryRepository dreamCardQueryRepository;
    private final ChallengeRepository challengeRepository;


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

        List<Auction> findAuctions = auctionRepository.findByDreamCardId(id);
        long auctionId = -1;
        if (!findAuctions.isEmpty()) auctionId = findAuctions.get(0).getAuctionId();

        ResponseFlipDreamCardDetail response = ResponseFlipDreamCardDetail.from(findCard, auctionId);

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

        long startTime = System.currentTimeMillis();
        log.info("start : " + startTime);
        // 여기서 일단 키워드로 mongoDB에 있는 dream을 다 뽑아야함
        ResponseDreamAnalysis responseDreamAnalysis = dreamAnalysisService.processAnalysis(request);

        if(responseDreamAnalysis == null){
            return ResultTemplate.builder().status(HttpStatus.BAD_REQUEST.value()).data("fail").build();
        }

        List<DreamKeyword> keywords = dreamKeywordRepository.findByKeywordIn(request.getKeywords());


        List<Challenge> recommendChallenges = challengeRepository.findRecommendChallengeByDreamCard(request.getKeywords())
                .stream().limit(5).collect(Collectors.toList());



        DreamCard makeDreamCard = DreamCard.makeDreamCard(request, author, keywords, fileName, responseDreamAnalysis);
        dreamCardRepository.save(makeDreamCard);

        // 챌린지 추천할꺼 추가
        ResponseDreamCardId response = ResponseDreamCardId.from(makeDreamCard, recommendChallenges, responseDreamAnalysis);
        long endTime = System.currentTimeMillis();
        log.info("endTime : " + endTime);

        log.info("totalTime : " + (double)(endTime - startTime) / 1000 + "ms");
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


    public ResultTemplate getInterpretationResult(String keyword) {

        List<Dream> findDreams = dreamAnalysisService.findDreamsByKeyword(keyword);
        if (findDreams.isEmpty()) throw new NotFoundException(NotFoundException.DREAM_NOT_FOUND);

        List<ResponseDreamAndDreamTelling> response = new ArrayList<>();
        for (Dream findDream : findDreams) {
            response.add(ResponseDreamAndDreamTelling.from(findDream));
        }

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @Transactional
    public ResultTemplate postImageName(Long dreamCardId, String fileName) {

        DreamCard findCard = dreamCardRepository.findById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        findCard.updateImageName(fileName);
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }
}
