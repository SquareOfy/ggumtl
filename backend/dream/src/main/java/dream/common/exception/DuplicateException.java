package dream.common.exception;

public class DuplicateException extends RuntimeException{

    public static final String USER_LIKE_DUPLICATE = "이미 좋아요 한 유저 입니다.";

    public static final String CHALLENGE_PARTICIPATION_DUPLICATE = "이미 참여 중인 챌린지 입니다.";
    public DuplicateException(String message) {
        super(message);
    }
}
