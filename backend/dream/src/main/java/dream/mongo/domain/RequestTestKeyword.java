package dream.mongo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTestKeyword {

    private List<String> keywords;
    private String content;
    private int positive;
    private int negative;

}
