package dream.common.exception;

public class InvalidAccessTokenException extends RuntimeException{

    public static String INVALID_ACCESS_TOKEN = "유효하지 않은 액세스 토큰입니다.";
    public InvalidAccessTokenException(String message){
        super(message);
    }
}
