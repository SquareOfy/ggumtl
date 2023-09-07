package dream.card.dto.response;

import dream.common.domain.BaseCheckType;

import java.time.LocalDateTime;

public class RequestDreamCardDto {
//    dreamCardid : 22222,
//    dreamCardOwner : 12345,
//    dreamCardAuthor : 67891,
//    createdAt : "2023/09/06",

//    ownerNickname : "yy",
//    ownerProfileUrl : "{profileUrl}"

//    likedNumber : 120,
//    isLike : true
    private long dreamCardId;
    private long dreamCardOwner;
    private String ownerNickName;
    private String ownerProfileUrl;
    private long dreanCardAuthor;

    private LocalDateTime createdAt;

    private int likedNumber;

    private BaseCheckType isLike;
}
