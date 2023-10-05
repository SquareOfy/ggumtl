package dream.notification.service;

import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.NoSuchElementException;
import dream.notification.domain.Notification;
import dream.notification.domain.NotificationQueryRepository;
import dream.notification.domain.NotificationRepository;
import dream.notification.dto.response.ResponseNotification;
import dream.notification.dto.response.ResponseNotificationListByAgoDays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import dream.user.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationQueryRepository notificationQueryRepository;

    private final Map<Long, String> tokenMap = new HashMap<>();

    public ResultTemplate getNotificationList(User user, Long lastItemId, int size) {

        List<Notification> list = notificationQueryRepository.getNotificationListByUserId(user.getUserId(), lastItemId, size);

        List<ResponseNotification> insertList = list.stream().map(notification -> {
            return ResponseNotification.from(notification);
        }).limit(size).collect(Collectors.toList());

        boolean hasNext = list.size() > size;

        ResponseNotificationListByAgoDays response = ResponseNotificationListByAgoDays.from(insertList, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();


    }


    @Transactional
    public ResultTemplate updateIsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> {
           throw new NoSuchElementException(NoSuchElementException.NO_SUCH_NOTIFICATION);
        });

        if(notification.getIsRead().equals(BaseCheckType.F)) notification.updateIsRead();

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public void register(final Long userId, final String token) {
        tokenMap.put(userId, token);
    }



}
