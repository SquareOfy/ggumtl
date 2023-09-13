package dream.common.exception;

public class NotMatchException extends RuntimeException {

    public static final String USER_LIKE_NOT_MATCH = "좋아요한 유저가 아닙니다.";
    public NotMatchException(String message) {
        super(message);
    }
}