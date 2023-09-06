package dream.notification.domain;

import dream.common.domain.BaseTimeEntity;
import dream.common.domain.BaseCheckType;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long notificationId;
    private String title;
    private String content;
    private BaseCheckType isRead;

    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private Long referenceId; //

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
