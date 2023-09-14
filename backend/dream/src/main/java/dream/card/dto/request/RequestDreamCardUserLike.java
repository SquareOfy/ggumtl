package dream.card.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDreamCardUserLike {

    private Long dreamCardId;
    private Long userId;

}
