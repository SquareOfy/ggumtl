package dream.card.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dream.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DreamCardLike {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dreamCardLikeId;

    @JoinColumn(name = "dream_card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DreamCard dreamCard;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
