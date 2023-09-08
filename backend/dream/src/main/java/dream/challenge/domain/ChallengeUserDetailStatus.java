package dream.challenge.domain;

import dream.common.domain.BaseCheckType;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeUserDetailStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeUserDetailStatus_id;

    @JoinColumn(name = "challenge_user_detail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeDetail challengeDetail;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private BaseCheckType isRead;
}
