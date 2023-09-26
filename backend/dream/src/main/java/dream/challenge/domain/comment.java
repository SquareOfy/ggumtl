package dream.challenge.domain;

import dream.challenge.dto.request.RequestComment;
import dream.common.domain.BaseUpdateTimeEntity;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class comment extends BaseUpdateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "detail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeDetail challengeDetail;

    private String content;

    public static comment makeComment(User user, RequestComment requestComment, ChallengeDetail challengeDetail) {

        comment pComment = new comment();

        pComment.challengeDetail = challengeDetail;
        pComment.user = user;
        pComment.content = requestComment.getContent();

        return pComment;
    }
}
