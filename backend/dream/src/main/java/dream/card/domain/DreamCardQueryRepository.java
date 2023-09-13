package dream.card.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static dream.card.domain.QDreamCard.dreamCard;
import static dream.challenge.domain.QChallenge.challenge;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DreamCardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<DreamCard> findDreamCardPaging(Long lastItemId, int size){

        QDreamCard dreamCard = QDreamCard.dreamCard;

        return queryFactory.selectFrom(dreamCard)
                .distinct()
                .leftJoin(dreamCard.dreamCardAuthor).fetchJoin()
                .leftJoin(dreamCard.dreamCardOwner).fetchJoin()
                .leftJoin(dreamCard.dreamCardLikes)
                .where(
                        lastItemIdLt(lastItemId)
                )
                .orderBy(dreamCard.dreamCardId.desc())
                .limit(size + 1)
                .fetch();
    }

    public Optional<DreamCard> findDetailById(Long id) {

        QDreamCard dreamCard = QDreamCard.dreamCard;
        return Optional.ofNullable(queryFactory.selectFrom(dreamCard)
                .distinct()
                .leftJoin(dreamCard.cardKeyword).fetchJoin()
                .where(
                        dreamCard.dreamCardId.eq(id)
                )
                .fetchOne());
    }


    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? dreamCard.dreamCardId.lt(lastItemId) : null;
    }


}
