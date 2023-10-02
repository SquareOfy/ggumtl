package dream.common.exception;

public class NotMatchException extends RuntimeException {

    public static final String USER_LIKE_NOT_MATCH = "좋아요한 유저가 아닙니다.";
    public static final String CARD_OWNER_MATCH = "카드 소유자가 아닙니다.";
    public static final String CARD_AUCTION_STATUS = "이미 경매 진행중인 카드 입니다.";


    public NotMatchException(String message) {
        super(message);
    }
}