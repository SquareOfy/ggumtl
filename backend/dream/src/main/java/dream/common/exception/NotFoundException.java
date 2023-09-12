package dream.common.exception;

public class NotFoundException extends RuntimeException {
    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CARD_LIST_NOT_FOUND = "생성된 꿈 카드가 없습니다.";
    public static final String CHALLENGE_LIST_NOT_FOUND = "생성된 챌린지가 없습니다.";

    public static final String DREAM_KEYWORD_NOT_FOUND = "생성된 키워드가 없습니다.";
    public static final String FOLLOWING_USER_NOT_FOUND = "팔로우한 유저가 없습니다.";

    public NotFoundException(String message) {
        super(message);
    }
}