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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private BaseCheckType isRead;

    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private Long referenceId;

    public void updateIsRead(){
        this.isRead = BaseCheckType.T;
    }

}
