package dream.s3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChallengeDetail {
    private Long challengeId;
    private String challengeDetailTitle;
    private String challengeDetailContent;
}
