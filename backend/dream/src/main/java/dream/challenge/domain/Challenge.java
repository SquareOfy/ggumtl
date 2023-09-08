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
public class Challenge extends BaseUpdateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "owner")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private String period;
    private String challengeTitle;
    private String challengeContent;

    private long hits;
    private String badgeUrl;
    private int timeCapsuleOpenAt;
}
