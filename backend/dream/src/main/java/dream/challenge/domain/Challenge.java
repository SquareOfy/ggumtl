package dream.challenge.domain;


import dream.challenge.dto.request.RequestChallenge;
import dream.common.domain.BaseUpdateTimeEntity;
import dream.s3.dto.request.RequestChallengeDetail;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseUpdateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    @JoinColumn(name = "owner")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private String period;
    private String challengeTitle;
    private String challengeContent;

    private long hits;
    private String badgeUrl;
    private int timeCapsuleOpenAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeKeyword> keywords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeParticipation> challengeParticipations;

    public void updateChallengeHits() {
        this.hits++;
    }

    public static Challenge makeChallenge(User user, RequestChallenge request){

        Challenge challenge = new Challenge();
        challenge.owner = user;
        challenge.challengeContent = request.getChallengeContent();
        challenge.challengeTitle = request.getChallengeTitle();
        challenge.badgeUrl = request.getBadgeUrl();
        challenge.period = request.getPeriod();
        // 로직 추가 필요
        challenge.timeCapsuleOpenAt = challenge.decideTimeCapsuleOpenAt(request.getPeriod());
        return challenge;
    }

    public int decideTimeCapsuleOpenAt(String period){

        int timeCapsuleOpenAt = 0;

        if(period.equals("7일")){
            timeCapsuleOpenAt = 4;
        } else if(period.equals("30일")){
            timeCapsuleOpenAt = 20;
        }

        return timeCapsuleOpenAt;
    }

}
