package dream.challenge.domain;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dream.user.domain.QFollow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static dream.challenge.domain.QChallenge.challenge;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Challenge> findChallengeListByPage(Long keywordId, Long lastItemId, int size) {

        QChallenge challenge = QChallenge.challenge;

        return queryFactory.selectFrom(challenge).distinct()
                        .leftJoin(challenge.keywords)
                        .where(
                                keywordIdEq(keywordId),
                                lastItemIdLt(lastItemId)
                        )
                        .orderBy(challenge.challengeId.desc())
                        .limit(size + 1)
                        .fetch();
    }

    public List<Challenge> getChallengeByKeyword(String searchKeyword, Long keywordId, Long lastItemId, int size) {

        QChallenge challenge = QChallenge.challenge;

        return queryFactory.selectFrom(challenge).distinct()
                .leftJoin(challenge.challengeParticipations)
                .where(
                        searchKeywordLike(searchKeyword),
                        keywordIdEq(keywordId),
                        lastItemIdLt(lastItemId)
                )
                .orderBy(challenge.challengeId.desc())
                .limit(size + 1)
                .fetch();
    }

    public List<Challenge> getChallengeByUserId(Long userId, Long lastItemId, int size) {

        QChallenge challenge = QChallenge.challenge;
        QChallengeParticipation challengeParticipation = QChallengeParticipation.challengeParticipation;

        JPAQuery<Long> subQuery = queryFactory.select(challengeParticipation.challenge.challengeId)
                .from(challengeParticipation)
                .where(
                        challengeParticipation.user.userId.eq(userId)
                );
        return queryFactory.selectFrom(challenge).distinct()
                .where(
                        challenge.challengeId.in(subQuery),
                        lastItemIdLt(lastItemId)
                )
                .orderBy(challenge.challengeId.desc())
                .limit(size + 1)
                .fetch();
    }

    private BooleanExpression searchKeywordLike(String searchKeyword){
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            String likeKeyword = "%" + searchKeyword + "%";
            return challenge.challengeTitle.like(likeKeyword);
        } else {
            return null;
        }
    }

    private BooleanExpression keywordIdEq(Long keywordId) {
        return keywordId != null ? challenge.keywords.any().keyword.keywordId.eq(keywordId) : null;
    }
    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? challenge.challengeId.lt(lastItemId) : null;
    }
}
