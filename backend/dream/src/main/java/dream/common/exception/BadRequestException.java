package dream.common.exception;

public class BadRequestException extends RuntimeException {

    public static final String NO_CHANGE_NICKNAME = "기존 닉네임과 동일합니다.";
    public static final String IMPOSSIBLE_FOLLOW_SELF = "잘못된 접근입니다. 자신을 팔로우할 수 없습니다.";
    public static final String UNFOLLOW_IMPOSSIBLE = "팔로우한 적이 없는 회원입니다.";

    public static final String NOT_EXIST_USER_PROFILE = "존재하지 않는 회원입니다.";

    public static final String NOT_ALLOWED_ACCESS = "잘못된 접근입니다. 조회 권한이 없습니다.";


    public BadRequestException(String message){
        super(message);
    }
}
