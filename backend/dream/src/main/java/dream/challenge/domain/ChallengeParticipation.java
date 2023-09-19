package dream.challenge.domain;


import dream.common.domain.BaseTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.PriorityQueue;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeParticipation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeParticipationId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "challenge_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @Enumerated(EnumType.STRING)
    private ChallengeStatus isIn;

    private String timeCapsuleContent;

    public static ChallengeParticipation createChallengeParticipation(User user, Challenge challenge) {

        ChallengeParticipation challengeParticipation = new ChallengeParticipation();
        challengeParticipation.user = user;
        challengeParticipation.challenge = challenge;
        challengeParticipation.isIn = ChallengeStatus.P;

        return challengeParticipation;
    }

    public void updateTimeCapsuleContent(String timeCapsuleContent) {
        this.timeCapsuleContent = timeCapsuleContent;
    }
}
