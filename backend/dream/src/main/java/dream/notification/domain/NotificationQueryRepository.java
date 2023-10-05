package dream.notification.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static dream.card.domain.QDreamCard.dreamCard;
import static dream.notification.domain.QNotification.notification;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

//    public List<Notification> getNotificationListByUserId(Long userId, Long lastItemId, int size){
//
//
//    }

    public List<Notification> getNotificationListByUserId(Long userId, Long lastItemId, int size) {

        QNotification notification = QNotification.notification;

        LocalDate today = LocalDate.now();

        LocalDateTime startDate;
                startDate = today.minusDays(6).atStartOfDay();



        return jpaQueryFactory
                .selectFrom(notification)
                .leftJoin(notification.user).fetchJoin()
                .where(
                        notification.user.userId.eq(userId),
                        notification.createdAt.between(startDate, today.minusDays(-1).atStartOfDay()),
                        lastItemIdLt(lastItemId)
                )
                .orderBy(notification.notificationId.desc())
                .limit(size+1)
                .fetch();
    }

    private BooleanExpression lastItemIdLt(Long lastItemId) {
        return lastItemId != null ? notification.notificationId.lt(lastItemId) : null;
    }
}
