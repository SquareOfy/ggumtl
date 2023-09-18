package dream.challenge.dto.response;

import dream.challenge.domain.Challenge;
import dream.challenge.domain.ChallengeDetail;
import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChallengeInfo {

    private boolean isParticipate;
    private int participateDay;
    private ResponseChallengeInfoDetail detail;

    public static ResponseChallengeInfo from(List<ChallengeDetail> challengeDetails
            , Challenge challengeWithKeyword, Challenge challengeWithParticipates, List<User> rankings){

        // 사용자 참여 및 참여일수 정보 주입
        boolean isParticipate = !challengeDetails.isEmpty();
        int participateDay = challengeDetails.size();

        List<ResponseChallengeUserRanking> userRankings = new ArrayList<>();
        int rank = 1;
        for (User ranking : rankings) {
            ResponseChallengeUserRanking tmpRank = ResponseChallengeUserRanking.from(ranking, rank);
            userRankings.add(tmpRank);
            rank++;
        }

        ResponseChallengeInfo response = new ResponseChallengeInfo();
        response.isParticipate = isParticipate;
        response.participateDay = participateDay;
        response.detail = ResponseChallengeInfoDetail.from(challengeWithKeyword, challengeWithParticipates, userRankings);

        return response;
    }
}
