package dream.challenge.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dream.challenge.domain.QBadge.badge;
import static dream.challenge.domain.QChallengeDetail.challengeDetail;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BadgeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Badge> findBadgeListByUserId(Long userId, Long lastItemId, int size){
        QBadge badge = QBadge.badge;

        return queryFactory.selectFrom(badge)
                .distinct()
                .where(
                        badge.user.userId.eq(userId),
                        lastItemIdLt(lastItemId)
                )
                .orderBy(badge.badgeId.desc())
                .limit(size+1)
                .fetch();
    }


    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? badge.badgeId.lt(lastItemId) : null;
    }
}
