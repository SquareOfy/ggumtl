package dream.auction.domain;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.card.domain.DreamCard;
import dream.card.domain.QCardKeyword;
import dream.card.domain.QDreamCard;
import dream.challenge.domain.ChallengeDetail;
import dream.challenge.domain.QChallengeDetail;
import dream.common.domain.BaseCheckType;
import dream.user.domain.QFollow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static dream.auction.domain.QAuction.auction;
import static dream.card.domain.QDreamCard.dreamCard;


@Slf4j
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuctionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Auction> findAuctionPaging(Long lastItemId, int size, String keyword) {

        QAuction auction = QAuction.auction;
        QDreamCard dreamCard = QDreamCard.dreamCard;
        QCardKeyword cardKeyword = QCardKeyword.cardKeyword;


        return queryFactory.selectFrom(auction)
                .distinct()
                .leftJoin(auction.dreamCard, dreamCard).fetchJoin()
                .leftJoin(dreamCard.cardKeyword, cardKeyword).fetchJoin()
                .leftJoin(cardKeyword.keyWordId).fetchJoin()
                .where(
                        lastItemIdLt(lastItemId),
                        dreamCard.auctionStatus.eq(BaseCheckType.T),
                        keywordIn(keyword)
                )
                .orderBy(auction.auctionId.desc())
                .limit(size + 1)
                .fetch();
    }

    //밤 프로필, 꿈 주기 탭 조회(경매 진행 중인 카드 목록 반환)
    public List<Auction> findAuctionOnSaleListByDreamCardOwner(Long userId, Long lastItemId, int size) {

        QAuction auction = QAuction.auction;
        QDreamCard dreamCard = QDreamCard.dreamCard;


        JPAQuery<Long> subQuery = queryFactory
                .select(auction.auctionId.max())
                .from(auction)
                .join(auction.dreamCard, dreamCard)
                .groupBy(dreamCard.dreamCardId);



       return queryFactory.selectFrom(auction)
                .leftJoin(auction.dreamCard, dreamCard).fetchJoin()
                .where(
                        auction.auctionId.in(subQuery),
                        lastItemIdLt(lastItemId),
                        dreamCard.auctionStatus.eq(BaseCheckType.T),
                        dreamCard.dreamCardOwner.userId.eq(userId)
                )
                .orderBy(auction.auctionId.desc())
                .limit(size+1)
                .fetch();


    }



    //밤 프로필, 꿈 받기 탭 조회(내가 참여중인 꿈경매에 해당되는 꿈카드 목록 반환)
    public List<Auction> findParticipatedAuctionListByDreamCardOwner(Long userId, Long lastItemId, int size) {
        QAuction auction = QAuction.auction;
        QDreamCard dreamCard = QDreamCard.dreamCard;
        QBidding bidding = QBidding.bidding;

        JPAQuery<Long> subQuery = queryFactory
                .select(auction.auctionId.max())
                .from(auction)
                .join(auction.dreamCard, dreamCard)
                .groupBy(dreamCard.dreamCardId);


        return queryFactory.selectFrom(auction)
                .distinct()
                .leftJoin(auction.dreamCard, dreamCard).fetchJoin()
                .leftJoin(bidding).fetchJoin()
                .on(auction.auctionId.eq(bidding.auction.auctionId))
                .where(
                        lastItemIdLt(lastItemId),
                        auction.auctionId.in(subQuery),
                        dreamCard.auctionStatus.eq(BaseCheckType.T),
                        bidding.user.userId.eq(userId)
                )
                .orderBy(auction.auctionId.desc())
                .limit(size + 1)
                .fetch();


    }

    public List<Bidding> findBiddingById(Long auctionId){

        QBidding bidding = QBidding.bidding;

        return queryFactory.selectFrom(bidding)
                .distinct()
                .leftJoin(bidding.user).fetchJoin()
                .where(
                        bidding.auction.auctionId.eq(auctionId)
                )
                .orderBy(bidding.biddingId.desc())
                .fetch();
    }
    public Optional<Bidding> findTopBiddingById(Long auctionId){

        QBidding bidding = QBidding.bidding;

        return Optional.ofNullable(queryFactory.selectFrom(bidding)
                .distinct()
                .leftJoin(bidding.user).fetchJoin()
                .where(
                        bidding.auction.auctionId.eq(auctionId)
                )
                .orderBy(bidding.biddingId.desc())
                .limit(1)
                .fetchOne());
    }



    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? auction.auctionId.lt(lastItemId) : null;
    }

    private BooleanExpression keywordIn(String keyword) {
        return keyword != null ? dreamCard.cardKeyword.any().keyWordId.keyword.like("%" + keyword + "%") : null;
    }
}