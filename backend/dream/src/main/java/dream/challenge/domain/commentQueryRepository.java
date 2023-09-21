package dream.challenge.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class commentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<comment> findCommentByPage(Long detailId, Long lastItemId, int size) {
        Qcomment comment = Qcomment.comment;

        return queryFactory.selectFrom(comment)
                .where(
                        comment.challengeDetail.challengeDetailId.eq(detailId),
                        lastItemIdLt(lastItemId)
                )
                // 시간 비교
                .orderBy(comment.createdAt.desc())
                .limit(size + 1)
                .fetch();
    }

    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? Qcomment.comment.commentId.lt(lastItemId) : null;
    }
}
