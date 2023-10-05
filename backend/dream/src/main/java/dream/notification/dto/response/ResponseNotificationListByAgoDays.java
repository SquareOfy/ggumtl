package dream.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ResponseNotificationListByAgoDays {

    private Map<Integer, List<ResponseNotification>> listByDays;
    private boolean hasNext;

    public static ResponseNotificationListByAgoDays from(List<ResponseNotification> list, boolean hasNext){
        ResponseNotificationListByAgoDays response = new ResponseNotificationListByAgoDays();

        LocalDate now = LocalDate.now();
        response.listByDays = list.stream()
                .collect(Collectors.groupingBy(
                        notification -> {
                            LocalDate createdAtDate = notification.getCreatedAt().toLocalDate();

                             long daysAgo = ChronoUnit.DAYS.between(createdAtDate, now);

                            if (daysAgo == 0) return 0;
                            if (daysAgo == 1) return 1;
                            if (daysAgo >= 2 && daysAgo <= 6) return 7;
                            return -1;  // For notifications older than 6 days or any other case
                        }
                ));

        // Remove the group for notifications older than 6 days or any other case
        response.listByDays.remove(-1);


        response.hasNext = hasNext;

        return response;

    }



}
