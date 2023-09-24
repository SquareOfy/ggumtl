package dream.common.exception;

public class BiddingException extends RuntimeException{
    public static final String LOW_BIDDING_MONEY = "최고 입찰가보다 낮은 금액입니다.";
    public static final String ALREADY_AUCTION_END = "이미 경매가 종료되었습니다.";
    public static final String BEFORE_AUCTION_END = "경매가 진행중 입니다.";
    public static final String HIGH_BIDDING_MONEY = "즉시 구매를 눌러주세요";
    public static final String NOT_ALLOW_AUCTION = "경매가 진행되었던 카드 입니다.";
    public static final String NOT_SAME_MONEY = "즉시 경매가와 일치하지 않습니다.";
    public static final String NOT_ENOUGH_MONEY = "꿈 포인트가 부족합니다.";
    public static final String USER_SAME_OWNER = "꿈 카드 소유자는 경매할 수 없습니다.";
    public static final String REVIEW_ONLY_OWNER = "꿈 카드 소유자만 리뷰를 남길 수 있습니다.";
    public static final String ALREADY_TIME_END = "경매 시간이 지났습니다.";
    public static final String ALREADY_MONEY_END = "더이상 입찰할 수 없습니다..";

    public BiddingException(String message){
        super(message);
    }
}
