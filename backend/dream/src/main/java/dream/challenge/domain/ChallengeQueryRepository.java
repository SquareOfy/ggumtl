package dream.challenge.domain;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static dream.challenge.domain.QChallenge.challenge;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Challenge> findChallengeListByPage(Long keywordId, Long lastItemId, int size) {

        QChallenge challenge = QChallenge.challenge;

        return queryFactory.selectFrom(challenge)
                        .join(challenge.keywords).fetchJoin()
                        .where(
                                keywordIdEq(keywordId),
                                challenge.challengeId.lt(lastItemId)
                        )
                        .orderBy(challenge.challengeId.desc())
                        .limit(size + 1)
                        .fetch();
    }

    private BooleanExpression keywordIdEq(Long keywordId) {
        return keywordId != null ? challenge.keywords.any().keyword.keywordId.eq(keywordId) : null;
    }


}
