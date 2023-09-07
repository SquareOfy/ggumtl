package dream.common.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ResultTemplate<T> {
    private HttpStatus status;
    private T data;
}
