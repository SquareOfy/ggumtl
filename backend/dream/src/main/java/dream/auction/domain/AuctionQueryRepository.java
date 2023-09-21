package dream.auction.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.card.domain.QCardKeyword;
import dream.card.domain.QDreamCard;
import dream.common.domain.BaseCheckType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Auction> findAuctionPaging(Long lastItemId, int size, String keyword){

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
                .fetchOne());
    }



    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? auction.auctionId.lt(lastItemId) : null;
    }

    private BooleanExpression keywordIn(String keyword) {
        return keyword != null ? dreamCard.cardKeyword.any().keyWordId.keyword.like("%"+keyword+"%") : null;
    }
}