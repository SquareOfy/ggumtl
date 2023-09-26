package dream.profile.dto.response;

import dream.user.domain.Follow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFollowerUserInfo {

        private Long followId;
        private Long userId;
        private String profileImageName;
        private String profileImageUrl;
        private String nickname;

        public static ResponseFollowerUserInfo from(Follow follow){
           ResponseFollowerUserInfo response = new ResponseFollowerUserInfo();

            response.followId = follow.getFollowId();
            response.userId = follow.getFromUser().getUserId();
            response.nickname=follow.getFromUser().getNickname();
            response.profileImageName=follow.getFromUser().getProfileImageName();
            response.profileImageUrl=follow.getFromUser().getProfileUrl();

            return response;
        }
}
