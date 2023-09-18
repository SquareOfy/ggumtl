package dream.user.domain;

import dream.common.domain.BaseCheckType;
import dream.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @JoinColumn(name = "from_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name = "to_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private BaseCheckType isRead;

    @Enumerated(EnumType.STRING)
    private Status status;
}
