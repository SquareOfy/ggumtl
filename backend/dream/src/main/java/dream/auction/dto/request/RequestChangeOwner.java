package dream.auction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChangeOwner {
    private long dreamCardId;
    private long newOwnerId;
}
