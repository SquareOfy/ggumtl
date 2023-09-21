package dream.common.exception;

public class NoSuchElementException extends RuntimeException {
    public static final String NO_SUCH_CREATED_CHALLENGE_LIST = "생성된 챌린지가 없습니다.";
    public static final String NO_SUCH_FOLLOWING_USER_STORY = "팔로우한 유저가 올린 글이 없습니다.";
    public static final String NO_SUCH_CHALLENGE_LIST = "해당 챌린지가 없습니다.";
    public static final String NO_SUCH_CHALLNENGE_PARTICIPATE = "해당 챌린지에 참여하고 있지 않습니다.";
    public static final String NO_SUCH_USER = "해당 유저가 없습니다.";
    public static final String NO_SUCH_USERID_IN_ACCESS_TOKEN = "ACCESS TOKEN 내에 유저 ID가 없습니다.";
    public static final String NO_SUCH_EMAIL_IN_REFRESH_TOKEN = "REFRESH TOKEN 내에 EMAIL이 없습니다.";
    public static final String NO_SUCH_USERID_IN_REFRESH_TOKEN = "REFRESH TOKEN 내에 유저 ID가 없습니다.";
    public static final String NO_SUCH_EXPIREAT_IN_ACCESS_TOKEN = "ACCESS TOKEN 내에 만료 기한이 없습니다.";
    public static final String NO_SUCH_ACCESSTOKEN_IN_HEADER = "헤더에 ACCESS TOKEN이 없습니다.";
    public static final String NO_SUCH_COMMENT = "해당 챌린지 인증글에 댓글이 존재하지 않습니다.";
    public static final String NO_SUCH_CHALLENGE_DETAIL = "해당 챌린지에 인증글이 없습니다.";


    public NoSuchElementException(String message) {
        super(message);
    }
}