package dream.common.exception;

public class DeleteException extends RuntimeException{


    public static final String DELETE_CARD_STATUS = "경매 진행중인 카드 입니다.";
    public static final String DELETE_CARD_OWNER = "경매 진행중인 카드 입니다.";
    public DeleteException(String message) {
        super(message);
    }


}
