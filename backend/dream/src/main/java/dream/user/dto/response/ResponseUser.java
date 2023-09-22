package dream.user.dto.response;


import dream.user.domain.Role;
import dream.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private long userId;
    private String name;
    private String nickname;
    private int point;
    private String profileImageName;
    private String profileImageUrl;
    private Double wrigglePoint;
    private String email;

    private Role role;

    public static ResponseUser from(User user){
        ResponseUser response = new ResponseUser();
        response.userId = user.getUserId();
        response.name = user.getName();
        response.nickname = user.getNickname();
        response.point = user.getPoint();
        response.profileImageUrl = user.getProfileUrl();
        response.profileImageName = user.getProfileImageName();
        response.wrigglePoint = user.getWrigglePoint();
        response.email = user.getEmail();
        response.role = user.getRole();

        return response;
    }

}
