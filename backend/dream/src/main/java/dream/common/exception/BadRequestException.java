package dream.common.exception;

public class BadRequestException extends RuntimeException {

    public static final String NO_CHANGE_NICKNAME = "기존 닉네임과 동일합니다.";
    public BadRequestException(String message){
        super(message);
    }
}
