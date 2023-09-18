package dream.auction.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.card.domain.DreamCard;
import dream.card.domain.QCardKeyword;
import dream.card.domain.QDreamCard;
import dream.common.domain.BaseCheckType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dream.auction.domain.QAuction.auction;
import static dream.card.domain.QCardKeyword.cardKeyword;
import static dream.card.domain.QDreamCard.dreamCard;

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

    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? auction.auctionId.lt(lastItemId) : null;
    }

    private BooleanExpression keywordIn(String keyword) {
        return keyword != null ? dreamCard.cardKeyword.any().keyWordId.keyword.like("%"+keyword+"%") : null;
    }
}
