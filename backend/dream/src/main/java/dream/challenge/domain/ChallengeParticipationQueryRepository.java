package dream.challenge.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dream.challenge.domain.QChallengeParticipation.challengeParticipation;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeParticipationQueryRepository {

    private final JPAQueryFactory queryFactory;
    public List<ChallengeParticipation> getFinishedChallengeListByUserIdPaging(Long userId, Long lastItemId, int size) {

        QChallenge challenge = QChallenge.challenge;
        QChallengeParticipation challengeParticipation = QChallengeParticipation.challengeParticipation;


        return queryFactory.selectFrom(challengeParticipation).distinct()
                .leftJoin(challengeParticipation.challenge).fetchJoin()
                .where(
                        challengeParticipation.user.userId.eq(userId),
                        challengeParticipation.isIn.eq(ChallengeStatus.S).or(challengeParticipation.isIn.eq(ChallengeStatus.F)),
                        lastItemIdLt(lastItemId)
                )
                .orderBy(challengeParticipation.challengeParticipationId.desc())
                .limit(size + 1)

                .fetch();
    }

    public List<ChallengeParticipation> getFinishedChallengeListByUserId(Long userId) {

        QChallenge challenge = QChallenge.challenge;
        QChallengeParticipation challengeParticipation = QChallengeParticipation.challengeParticipation;


        return queryFactory.selectFrom(challengeParticipation).distinct()
                .leftJoin(challengeParticipation.challenge).fetchJoin()
                .where(
                        challengeParticipation.user.userId.eq(userId),
                        challengeParticipation.isIn.eq(ChallengeStatus.S).or(challengeParticipation.isIn.eq(ChallengeStatus.F))
                )
                .orderBy(challengeParticipation.challengeParticipationId.desc())
                .fetch();
    }
    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? challengeParticipation.challengeParticipationId.lt(lastItemId) : null;
    }
}
