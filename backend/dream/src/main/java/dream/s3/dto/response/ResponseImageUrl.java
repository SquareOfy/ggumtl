package dream.s3.dto.response;

import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseImageUrl {
    private String profileImageUrl;

    public static ResponseImageUrl from(User findUser) {

        ResponseImageUrl response = new ResponseImageUrl();
        response.profileImageUrl = findUser.getProfileUrl();

        return response;
    }
}
