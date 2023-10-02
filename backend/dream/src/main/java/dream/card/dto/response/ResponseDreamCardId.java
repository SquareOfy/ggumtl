package dream.card.dto.response;

import dream.card.domain.DreamCard;
import dream.card.domain.Grade;
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
    private int rarePoint;
    private Grade grade;
    private String dreamTelling;
    private Grade positiveGrade;
    private Grade rareGrade;

    public static ResponseDreamCardId from(DreamCard dreamCard, List<Challenge> challengeList, ResponseDreamAnalysis responseDreamAnalysis){
        ResponseDreamCardId response = new ResponseDreamCardId();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.challengeList = challengeList.stream().
                map(Challenge::getChallengeId).collect(Collectors.toList());
        response.rarePoint = responseDreamAnalysis.getRarePoint();
        response.grade = responseDreamAnalysis.getGrade();
        response.dreamTelling = responseDreamAnalysis.getDreamTelling();
        response.positiveGrade = responseDreamAnalysis.getPositiveGrade();
        response.rareGrade = responseDreamAnalysis.getRareGrade();

        return response;
    }
}