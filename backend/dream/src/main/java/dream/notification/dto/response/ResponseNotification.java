package dream.notification.dto.response;

import dream.common.domain.BaseCheckType;
import dream.notification.domain.Notification;
import dream.notification.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNotification {

    private Long notificationId;
    private LocalDateTime createdAt;
    private NotificationType type;
    private Long referenceId;
    private String title;
    private String content;
    private BaseCheckType isRead;

    public static ResponseNotification from(Notification notification){
        ResponseNotification response = new ResponseNotification();

        response.notificationId = notification.getNotificationId();
        response.createdAt = notification.getCreatedAt();
        response.type = notification.getType();
        response.referenceId = notification.getReferenceId();
        response.title = notification.getTitle();
        response.content = notification.getContent();
        response.isRead = notification.getIsRead();

        return response;



    }
}
