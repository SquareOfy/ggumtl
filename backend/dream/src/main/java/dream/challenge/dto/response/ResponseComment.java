package dream.challenge.dto.response;

import dream.challenge.domain.comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseComment {

    private Long commentId;
    private Long userId;
    private String nickname;
    private String content;
    private String profileImageUrl;

    public static ResponseComment from(comment comment) {

        ResponseComment response = new ResponseComment();
        response.content = comment.getContent();
        response.nickname = comment.getUser().getNickname();
        response.userId = comment.getUser().getUserId();
        response.commentId = comment.getCommentId();
        response.profileImageUrl = comment.getUser().getProfileUrl();
        return response;
    }
}
