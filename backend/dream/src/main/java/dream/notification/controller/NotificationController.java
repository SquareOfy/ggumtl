package dream.notification.controller;

import dream.common.domain.ResultTemplate;
import dream.notification.dto.request.RequestNotificationId;
import dream.notification.service.NotificationService;
import dream.security.jwt.domain.UserInfo;
import dream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import dream.user.domain.User;
@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/list")
    public ResultTemplate getNotificationList(@UserInfo User user, @RequestParam(value = "lastItemId", required = false)
                                                    Long lastItemId, @RequestParam("size") int size){

        return notificationService.getNotificationList(user, lastItemId, size);
    }

    @PutMapping("/state")
    public ResultTemplate updateNotificationIsRead(@RequestBody RequestNotificationId requestNotificationId){
        return notificationService.updateIsRead(Long.valueOf(requestNotificationId.getNotificationId()));
    }
}

