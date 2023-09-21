package dream.user.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.card.domain.DreamCard;
import dream.card.domain.QDreamCard;
import dream.common.domain.BaseCheckType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dream.card.domain.QDreamCard.dreamCard;
import static dream.user.domain.QFollow.follow;

@Slf4j
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Follow> findFollowByFromId(Long fromId, Long lastFollowId, int size){

        QFollow follow = QFollow.follow;

        return queryFactory.selectFrom(follow)
                .distinct()
                .leftJoin(follow.fromUser).fetchJoin()
                .where(follow.fromUser.userId.eq(fromId),
                        lastItemIdLt(lastFollowId))
                .orderBy(follow.followId.asc())
                .limit(size + 1)
                .fetch();


    }

    public List<Follow> findFollowByToId(Long toId, Long lastFollowId, int size){

        QFollow follow = QFollow.follow;

        return queryFactory.selectFrom(follow)
                .distinct()
                .leftJoin(follow.toUser).fetchJoin()
                .where(follow.toUser.userId.eq(toId),
                        lastItemIdLt(lastFollowId))
                .orderBy(follow.followId.asc())
                .limit(size + 1)
                .fetch();


    }

    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? follow.followId.lt(lastItemId) : null;
    }


//    public List<DreamCard> findDreamCardPaging(Long lastItemId, int size){
//
//        QDreamCard dreamCard = QDreamCard.dreamCard;
//
//        return queryFactory.selectFrom(dreamCard)
//                .distinct()
//                .leftJoin(dreamCard.dreamCardAuthor).fetchJoin()
//                .leftJoin(dreamCard.dreamCardOwner).fetchJoin()
//                .leftJoin(dreamCard.dreamCardLikes)
//                .where(
//                        lastItemIdLt(lastItemId),
//                        dreamCard.isShow.eq(BaseCheckType.T)
//                )
//                .orderBy(dreamCard.dreamCardId.desc())
//                .limit(size + 1)
//                .fetch();
//    }


}
