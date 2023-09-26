package dream.challenge.dto.response;

import dream.challenge.domain.ChallengeParticipation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTimeCapsule {
    private Long userId;
    private Long timeCapsuleId;
    private String timeCapsuleContent;

    public static ResponseTimeCapsule from(ChallengeParticipation challengeParticipation) {

        ResponseTimeCapsule response = new ResponseTimeCapsule();

        response.userId = challengeParticipation.getUser().getUserId();
        response.timeCapsuleId = challengeParticipation.getChallengeParticipationId();
        response.timeCapsuleContent = challengeParticipation.getTimeCapsuleContent();

        return response;
    }
}
