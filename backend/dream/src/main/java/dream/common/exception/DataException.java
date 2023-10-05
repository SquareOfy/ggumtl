package dream.common.exception;

public class DataException extends RuntimeException{
    public static final String NO_KEYWORDID_FOUND = " 번 챌린지에 연결된 키워드가 없습니다.";

    public DataException(String message, Long id) {
        super(id+message) ;
    }
}
