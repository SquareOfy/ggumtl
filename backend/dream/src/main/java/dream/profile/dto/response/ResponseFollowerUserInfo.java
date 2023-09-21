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
        private String profileImgName;
        private String profileImgUrl;
        private String nickname;

        public static ResponseFollowerUserInfo from(Follow follow){
           ResponseFollowerUserInfo response = new ResponseFollowerUserInfo();

            response.followId = follow.getFollowId();
            response.userId = follow.getFromUser().getUserId();
            response.nickname=follow.getFromUser().getNickname();
            response.profileImgName=follow.getFromUser().getProfileImageName();
            response.profileImgUrl=follow.getFromUser().getProfileUrl();

            return response;
        }
}
