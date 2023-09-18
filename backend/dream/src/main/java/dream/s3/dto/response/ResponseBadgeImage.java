package dream.s3.dto.response;

import dream.challenge.domain.Challenge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBadgeImage {
    private String badgeUrl;

    public static ResponseBadgeImage from(Challenge challenge) {

        ResponseBadgeImage response = new ResponseBadgeImage();

        // 여기서 환경변수에서 S3해당 경로 맞춰줘야함
        // DB에는 이미지 저장 이름이 담길거고 환경 변수로 경로 처리 붙여서 프론트로 줘야함
        // 경로를 저장하지 않는 이유는 확장성
        response.badgeUrl = challenge.getBadgeUrl();
        return response;
    }
}
