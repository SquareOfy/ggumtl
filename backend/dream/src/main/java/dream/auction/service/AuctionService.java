package dream.auction.service;

import dream.auction.domain.Auction;
import dream.auction.domain.AuctionQueryRepository;
import dream.auction.domain.AuctionRepository;
import dream.auction.domain.Bidding;
import dream.auction.dto.request.RequestAuction;
import dream.auction.dto.request.RequestBidding;
import dream.auction.dto.request.RequestCardReview;
import dream.auction.dto.request.RequestChangeOwner;
import dream.auction.dto.response.ResponseAuction;
import dream.auction.dto.response.ResponseAuctionDetail;
import dream.auction.dto.response.ResponseAuctionList;
import dream.auction.dto.response.ResponseBidding;
import dream.card.domain.CardKeyword;
import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardRepository;
import dream.card.domain.WriggleReview;
import dream.card.dto.request.RequestDreamCardId;
import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BiddingException;
import dream.common.exception.NotFoundException;
import dream.common.exception.NotMatchException;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionQueryRepository auctionQueryRepository;
    private final DreamCardRepository dreamCardRepository;
    private final UserRepository userRepository;
    private final EntityManager em;
    private final AuctionListener auctionListener;

    // 경매 등록하는 함수
    public ResultTemplate postAuction(Long dreamCardId, RequestAuction request, Long userId) {

        DreamCard findCard = dreamCardRepository.findAuctionById(dreamCardId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        if (!findCard.getDreamCardOwner().getUserId().equals(userId)) throw new NotMatchException(NotMatchException.CARD_OWNER_MATCH);

        if (findCard.getAuctionStatus().equals(BaseCheckType.T)) throw new NotMatchException(NotMatchException.CARD_AUCTION_STATUS);
        // 경매에 두 번 이상 올라올 수 있는 경우 정의하고 다시
        // if (!findCard.getAuction().isEmpty()) throw new BiddingException(BiddingException.NOT_ALLOW_AUCTION);

        findCard.insertAuction();

        Auction auction = Auction.createAuction(findCard, request);
        auctionRepository.save(auction);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 전체 경매등록 카드들 조회 함수
    public ResultTemplate getAllAuctionList(Long lastItemId, int size, String keyword) {

        List<Auction> findAuctions = auctionQueryRepository.findAuctionPaging(lastItemId, size, keyword);
        if (findAuctions.isEmpty()) throw new NotFoundException(NotFoundException.AUCTION_LIST_NOT_FOUND);
        List<CardKeyword> cardKeyword = findAuctions.get(0).getDreamCard().getCardKeyword();

        for (CardKeyword cardKeyword1 : cardKeyword) {
            log.info("{}", cardKeyword1.getKeyWordId().getKeyword());
        }

        List<ResponseAuction> auctions = findAuctions.stream()
                .limit(size)
                .map(ResponseAuction::from)
                .collect(Collectors.toList());

        boolean hasNext = findAuctions.size() > size;
        ResponseAuctionList list = ResponseAuctionList.from(auctions, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(list).build();
    }

    // 키워드를 통해 경매 등록 카드들 조회
    public ResultTemplate getAuctionListByKeyword(String keyword) {
        // 명세에 response 데이터가 없어서 DTO 아직 못 만들었어요
        // 벌써 여기를 개발중이시라면 당신은 속도가 어마어마하시네요
        // 분명 성공할거에요 좋은 곳 취업해서 우리를 잊지말아요 - 0908 21:46 선진올림
//        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        return null;
    }

    // 경매 상세 조회 함수 - 난이도 매우 어려워보임
    public ResultTemplate getAuctionDetail(Long id) {


        Auction findAuction = auctionRepository.findAuctionDetailById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.AUCTION_NOT_FOUND));

        List<Bidding> findBidding = auctionQueryRepository.findBiddingById(id);
        if (findBidding.isEmpty()) throw new NotFoundException(NotFoundException.BIDDING_NOT_FOUND);

        ResponseAuctionDetail response = ResponseAuctionDetail.from(findAuction, findBidding);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    // 입찰 등록 함수 - 유저 같이 매개변수로 받아와서 처리부탁드립니다.
    public void postBidding(RequestBidding request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Auction findAuction = auctionRepository.findBiddingById(request.getAuctionId(), LocalDateTime.now())
                .orElseThrow(() -> new NotFoundException(NotFoundException.AUCTION_NOT_FOUND));

        if (BaseCheckType.F.equals(findAuction.getDreamCard().getAuctionStatus())) throw new BiddingException(BiddingException.ALREADY_AUCTION_END);
        if (findAuction.getBidding().isEmpty()) throw new NotFoundException(NotFoundException.BIDDING_NOT_FOUND);

        Bidding topBidding = findAuction.getBidding().get(0);
        if (request.getBiddingMoney() <= topBidding.getBiddingMoney()) throw new BiddingException(BiddingException.LOW_BIDDING_MONEY);
        if (request.getBiddingMoney() >= findAuction.getImmediatelyBuyMoney()) throw new BiddingException(BiddingException.HIGH_BIDDING_MONEY);

        findAuction.addBidding(user, request.getBiddingMoney());
        em.flush();

        Bidding findBidding = auctionQueryRepository.findTopBiddingById(request.getAuctionId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.BIDDING_NOT_FOUND));
        ResponseBidding response = ResponseBidding.from(request.getAuctionId(), findBidding);

        auctionListener.sendBidding(response);

    }

    // 카드 즉시구매 함수 - 유저 같이 매개변수로 받아야해요.
    // 그리고 이외 처리할 서비스 로직이 넘쳐납니다.
    // 예외 처리할 것도 넘쳐나네요.
    public ResultTemplate purchaseDreamCard(RequestBidding request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Auction findAuction = auctionRepository.findBiddingById(request.getAuctionId(), LocalDateTime.now())
                .orElseThrow(() -> new NotFoundException(NotFoundException.AUCTION_NOT_FOUND));

        if (BaseCheckType.F.equals(findAuction.getDreamCard().getAuctionStatus())) throw new BiddingException(BiddingException.ALREADY_AUCTION_END);
        if (findAuction.getBidding().isEmpty()) throw new NotFoundException(NotFoundException.BIDDING_NOT_FOUND);

        if (request.getBiddingMoney() != findAuction.getImmediatelyBuyMoney()) throw new BiddingException(BiddingException.NOT_SAME_MONEY);
        if (user.getPoint() < request.getBiddingMoney()) throw new BiddingException(BiddingException.NOT_ENOUGH_MONEY);

        findAuction.addBidding(user, request.getBiddingMoney());
        em.flush();
        user.purchaseDreamCard(request.getBiddingMoney());

        DreamCard findDreamCard = dreamCardRepository.findReviewById(findAuction.getDreamCard().getDreamCardId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));

        findDreamCard.addReview(user, findDreamCard.getDreamCardOwner());

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("즉시 구매 성공!").build();
    }

    // 최종 입찰 성공해서 카드 주인 바꾸기 함수
    // 유저 같이 넘겨와야해요
    public ResultTemplate successBiddingAndOwnerChange(RequestChangeOwner request) {

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    // 꿈 산 후 리뷰 등록하는 함수
    // 할거 짱 많을거에요
    public ResultTemplate postBuyingCardReview(RequestCardReview request) {

        DreamCard findDreamCard = dreamCardRepository.findReviewById(request.getDreamCardId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.CARD_NOT_FOUND));
        // 로그인 유저가 카드 산 사람인지 예외 처리 해야 해
        List<WriggleReview> reviews = findDreamCard.getWriggleReviews();
        if (reviews.isEmpty()) throw new NotFoundException(NotFoundException.REVIEW_NOT_FOUND);
        WriggleReview wriggleReview = reviews.get(0);
        wriggleReview.updateReview(request);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }
}
