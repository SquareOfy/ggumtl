package dream.user.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @JoinColumn(name = "from_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name = "to_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    public static Follow createFollow(User fromUser, User toUser){
        Follow follow = new Follow();
        follow.fromUser = fromUser;
        follow.toUser = toUser;

        return follow;

    }

}
