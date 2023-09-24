package dream.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDream{
    String sentence;
    String dreamTelling;
    int positivePoint;
    int negativePoint;
    int tellingPositivePoint;
    int tellingNegativePoint;
    List<String> keywords;
}
