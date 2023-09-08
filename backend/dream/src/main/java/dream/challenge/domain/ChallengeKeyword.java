package dream.challenge.domain;

import dream.card.domain.DreamKeyword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeKeyword {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeKeywordId;

    @JoinColumn(name = "challenge_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @JoinColumn(name = "dream_keyword")
    @ManyToOne(fetch = FetchType.LAZY)
    private DreamKeyword keyword;

}
