package dream.mongo.domain;

import dream.s3.Analysis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document(collection = "dream")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dream {

    @Id
    private String id;
    private String dream;
    private Analysis analysis;

    public Dream(String dream){
        this.dream = dream;
    }
}
