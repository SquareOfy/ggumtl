package dream.common.exception;

public class MethodArgumentNotValidException extends RuntimeException {
    public static final String CHALLENGE_ID_NOT_VALID = "챌린지 아이디는 필수 매개변수입니다.";
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
