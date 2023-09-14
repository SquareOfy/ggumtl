package dream.common.exception;

public class NotFoundException extends RuntimeException {
    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CARD_LIST_NOT_FOUND = "생성된 꿈 카드가 없습니다.";
    public static final String CARD_NOT_FOUND = "존재하지 않는 꿈 카드입니다.";
    public static final String DREAM_KEYWORD_NOT_FOUND = "생성된 키워드가 없습니다.";
    public static final String CHALLENGE_NOT_FOUND = "존재하지 않는 챌린지입니다.";

    public NotFoundException(String message) {
        super(message);
    }
}