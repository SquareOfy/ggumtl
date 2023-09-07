package dream.common.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ResultTemplate<T> {
    private HttpStatus status;
    private T data;
}
