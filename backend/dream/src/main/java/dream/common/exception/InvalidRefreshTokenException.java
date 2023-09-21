package dream.common.exception;

public class InvalidRefreshTokenException extends RuntimeException{

    public static final String INVALID_REFRESH_TOKEN = "유효하지 않은 리프레쉬 토큰입니다.";
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}
