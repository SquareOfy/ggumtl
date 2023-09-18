package dream.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class RefreshTokenDto {

    private Long userId;

    @Id
    private String refreshToken;



}
