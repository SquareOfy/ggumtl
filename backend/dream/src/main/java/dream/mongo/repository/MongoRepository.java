package dream.mongo.repository;

import dream.mongo.domain.Dream;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Dream, String> {

    List<Dream> findAll();

    List<Dream> findByDreamRegex(String title);

    @Query("{'dream': { $in: ?#{ [0].stream().map(keyword -> new org.bson.Document('$regex', keyword).append('$options', 'i')).collect(java.util.stream.Collectors.toList()) } }")
    List<Dream> findByDreamRegexList(List<String> keywords);
}