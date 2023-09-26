package dream.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDream {
    String sentence;
    int positivePoint;
    int negativePoint;
    List<String> keywords;
}
