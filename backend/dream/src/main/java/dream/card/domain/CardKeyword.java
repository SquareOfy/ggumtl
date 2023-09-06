package dream.card.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardKeyword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardKeywordId;

    @JoinColumn(name = "card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DreamCard cardId;

    @JoinColumn(name = "keyword_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Keyword keyWordId;


}
