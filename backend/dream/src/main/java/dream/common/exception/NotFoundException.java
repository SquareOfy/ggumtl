package dream.common.exception;

public class NotFoundException extends RuntimeException {
    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CARD_LIST_NOT_FOUND = "생성된 꿈 카드가 없습니다.";
    public static final String CARD_NOT_FOUND = "존재하지 않는 꿈 카드입니다.";
    public static final String DREAM_KEYWORD_NOT_FOUND = "생성된 키워드가 없습니다.";
    public static final String CHALLENGE_NOT_FOUND = "존재하지 않는 챌린지입니다.";
    public static final String AUCTION_LIST_NOT_FOUND = "생성된 경매가 없습니다.";
    public static final String CHALLENGE_DETAIL_NOT_FOUND = "존재하지 않는 인증 글입니다.";
    public static final String COMMENT_NOT_FOUND = "존재하지 않는 댓글입니다.";
    public static final String FOLLOWING_NOT_FOUND = "팔로우하는 유저가 없습니다.";
    public static final String FOLLOWER_NOT_FOUND = "팔로잉하는 유저가 없습니다.";
    public static final String AUCTION_NOT_FOUND = "존재하는 경매가 없습니다.";
    public static final String BIDDING_NOT_FOUND = "존재하는 입찰이 없습니다.";
    public static final String REVIEW_NOT_FOUND = "존재하는 리뷰가 없습니다.";
    public static final String DREAM_NOT_FOUND = "해몽 내용이 없습니다.";
    public static final String KEYWORD_NOT_FOUND = "해몽 내용이 없습니다.";


    public NotFoundException(String message) {
        super(message);
    }
}