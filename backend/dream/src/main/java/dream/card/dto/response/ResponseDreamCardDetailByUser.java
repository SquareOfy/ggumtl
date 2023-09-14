package dream.card.dto.response;

import dream.card.domain.CardKeyword;
import dream.card.domain.DreamCard;
import dream.card.domain.Grade;
import dream.common.domain.BaseCheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDreamCardDetailByUser {
    private long dreamCardId;
    private long dreamCardAuthor;
    private long dreamOwner;
    private String dreamCardContent;
    private String dreamTelling;
    private String dreamCardImageUrl;
    private Grade grade;
    private Grade positiveGrade;
    private Grade rareGrade;
    private LocalDateTime createdAt;
    private BaseCheckType actionStatus;
    private BaseCheckType isShow;
    private List<ResponseKeyword> keywords;
    private int likeCount;
    private BaseCheckType reviewStatus;

    public static ResponseDreamCardDetailByUser from(DreamCard dreamCard, BaseCheckType reviewStatus){

        ResponseDreamCardDetailByUser response = new ResponseDreamCardDetailByUser();
        response.dreamCardId = dreamCard.getDreamCardId();
        response.dreamCardAuthor = dreamCard.getDreamCardAuthor().getUserId();
        response.dreamOwner = dreamCard.getDreamCardOwner().getUserId();
        response.dreamCardContent = dreamCard.getDreamCardContent();
        response.dreamTelling = dreamCard.getDreamTelling();;
        response.dreamCardImageUrl = dreamCard.getDreamCardImageUrl();;
        response.grade = dreamCard.getGrade();
        response.positiveGrade = dreamCard.getPositiveGrade();
        response.rareGrade = dreamCard.getRareGrade();
        response.createdAt = dreamCard.getCreatedAt();
        response.actionStatus = dreamCard.getAuctionStatus();
        response.isShow = dreamCard.getIsShow();

        List<ResponseKeyword> keywords = new ArrayList<>();
        List<CardKeyword> cardKeywords = dreamCard.getCardKeyword();
        for (CardKeyword cardKeyword : cardKeywords) {
            keywords.add(ResponseKeyword.from(cardKeyword));
        }
        response.keywords = keywords;
        response.likeCount = dreamCard.getDreamCardLikes().size();
        response.reviewStatus = reviewStatus;

        return response;
    }
}
