package dream.s3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analysis {

    private List<String> keywords;
    private String dreamTelling;
    private int dreamPositivePoint;
    private int dreamNegativePoint;
    private int dreamTellingPositivePoint;
    private int dreamTellingNegativePoint;
}
