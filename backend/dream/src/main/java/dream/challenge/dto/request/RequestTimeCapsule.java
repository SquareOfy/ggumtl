package dream.challenge.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTimeCapsule {
    @NotBlank(message = "챌린지 아이디는 필수 입력값입니다.")
    private Long ChallengeId;

    @NotBlank(message = "타임캡슐 내용은 필수 입력값입니다.")
    private String timeCapsuleContent;
}
