package dream.common.exception;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException(String message) {
        super("유효하지 않은 리프레쉬 토큰입니다.");
    }
}
