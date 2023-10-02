package dream.common.exception;

public class NoSuchElementException extends RuntimeException {
    public static final String NO_SUCH_CREATED_CHALLENGE_LIST = "생성된 챌린지가 없습니다.";
    public static final String NO_SUCH_FOLLOWING_USER_STORY = "팔로우한 유저가 올린 글이 없습니다.";
    public static final String NO_SUCH_CHALLENGE_LIST = "해당 챌린지가 없습니다.";
    public static final String NO_SUCH_CHALLNENGE_PARTICIPATE = "해당 챌린지에 참여하고 있지 않습니다.";

    public NoSuchElementException(String message) {
        super(message);
    }
}