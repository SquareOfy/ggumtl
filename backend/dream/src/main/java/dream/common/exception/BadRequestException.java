package dream.common.exception;

public class BadRequestException extends RuntimeException{

    public static final String NO_CHANGE_NICKNAME = "기존 닉네임과 동일한 닉네임입니다.";

    public static final String IMPOSSIBLE_FOLLOW_SELF = "팔로우 불가한 대상입니다.";

    public static final String UNFOLLOW_IMPOSSIBLE = "팔로우한 적이 없는 회원입니다.";
    public BadRequestException(String message){
        super(message);
    }
}
