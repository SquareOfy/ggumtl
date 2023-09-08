package dream.challenge.domain;


import dream.common.domain.BaseUpdateTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeDetail extends BaseUpdateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeDetailId;

    @JoinColumn(name = "challenge_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String photoUrl;
    private String challengeDetailTitle;
    private String challengeDetailContent;

    private long hits;
    private long maxDays;

}
