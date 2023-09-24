package dream.notification.controller;

import dream.common.domain.ResultTemplate;
import dream.notification.dto.request.RequestNotificationId;
import dream.notification.service.NotificationService;
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
    private final UserService userService;

    @GetMapping("/list")
    public ResultTemplate getNotificationList(@RequestParam(value = "lastItemId", required = false)
                                                    Long lastItemId, @RequestParam("size") int size){
        User user = userService.getUserForDev(1L);
        log.info("USERID : {} ",user.getUserId());
        return notificationService.getNotificationList(user, lastItemId, size);
    }

    @PutMapping("/state")
    public ResultTemplate updateNotificationIsRead(@RequestBody RequestNotificationId requestNotificationId){
        return notificationService.updateIsRead(Long.valueOf(requestNotificationId.getNotificationId()));
    }
}

