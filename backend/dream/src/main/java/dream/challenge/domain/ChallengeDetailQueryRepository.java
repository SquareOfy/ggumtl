package dream.challenge.domain;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.user.domain.Follow;
import dream.user.domain.QFollow;
import dream.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static dream.challenge.domain.QChallenge.challenge;
import static dream.challenge.domain.QChallengeDetail.challengeDetail;

@Slf4j
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeDetailQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<ChallengeDetail> findChallengeListByPage(Long userId, Long lastItemId, int size) {
        QChallengeDetail challengeDetail = QChallengeDetail.challengeDetail;
        QFollow follow = QFollow.follow;
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        JPAQuery<Long> subQuery = queryFactory.select(follow.toUser.userId)
                .from(follow)
                .where(
                        follow.fromUser.userId.eq(userId)
                );

        return queryFactory.selectFrom(challengeDetail)
                .where(
                        challengeDetail.user.userId.in(subQuery),
                        lastItemIdLt(lastItemId),
                        challengeDetail.createdAt.between(startOfDay, endOfDay)
                )
                // 시간 비교
                .orderBy(challengeDetail.challengeDetailId.desc())
                .limit(size + 1)
                .fetch();
    }

    public List<ChallengeDetail> getStoryByUserId(long userId) {
        QChallengeDetail challengeDetail = QChallengeDetail.challengeDetail;
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        return queryFactory.selectFrom(challengeDetail)
                .where(
                        challengeDetail.user.userId.eq(userId),
                        challengeDetail.createdAt.between(startOfDay, endOfDay)
                )
                .fetch();
    }

    public List<ChallengeDetail> getIsUserParticipateChallenge(long userId, long challengeId){
        QChallengeDetail challengeDetail = QChallengeDetail.challengeDetail;

        return queryFactory.selectFrom(challengeDetail)
                .where(
                        challengeDetail.user.userId.eq(userId),
                        challengeDetail.challenge.challengeId.eq(challengeId)
                )
                .fetch();
    }

    public List<User> getRank(long challengeId){
        QChallengeDetail challengeDetail = QChallengeDetail.challengeDetail;

        return queryFactory.select(challengeDetail.user)
                .from(challengeDetail)
                .where(
                        challengeDetail.challenge.challengeId.eq(challengeId)
                )
                .groupBy(challengeDetail.user.userId, challengeDetail.challenge.challengeId)
                .orderBy(challengeDetail.count().desc())
                .limit(3)
                .fetch();
    }


    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? challengeDetail.challengeDetailId.lt(lastItemId) : null;
    }
}
