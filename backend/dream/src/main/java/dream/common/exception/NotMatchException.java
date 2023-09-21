package dream.common.exception;

public class NotMatchException extends RuntimeException {

    public static final String USER_LIKE_NOT_MATCH = "꿈 카드를 좋아요한 유저가 아닙니다.";
    public static final String CARD_OWNER_MATCH = "카드 소유자가 아닙니다.";
    public static final String CARD_AUCTION_STATUS = "이미 경매 진행중인 카드 입니다.";
    public static final String USER_STATUS = "이미 가입한 회원입니다.";
    public static final String USER_DETAIL_LIKE_NOT_MATCH = "해당 챌린지 인증글을 좋아요 한 유저가 아닙니다.";

    public NotMatchException(String message) {
        super(message);
    }
}