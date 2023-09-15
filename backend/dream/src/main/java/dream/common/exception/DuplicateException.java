package dream.common.exception;

public class DuplicateException extends RuntimeException{

    public static final String USER_LIKE_DUPLICATE = "이미 좋아요한 유저 입니다.";
    public DuplicateException(String message) {
        super(message);
    }
}
