package dream.card.dto.response;

import dream.card.domain.DreamCard;
import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardId {
    private Long dreamCardId;
    private List<Long> challengeList;

    public static ResponseDreamCardId from(DreamCard dreamCard, List<Challenge> challengeList){
        ResponseDreamCardId response = new ResponseDreamCardId();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.challengeList = challengeList.stream().
                map(Challenge::getChallengeId).collect(Collectors.toList());
        return response;
    }
}