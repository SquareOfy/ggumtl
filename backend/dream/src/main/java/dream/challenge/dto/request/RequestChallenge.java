package dream.challenge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChallenge {
    private String challengeTitle;
    private String challengeContent;
    private String badgeUrl;
    private long keywordId;
    private String period;
}
