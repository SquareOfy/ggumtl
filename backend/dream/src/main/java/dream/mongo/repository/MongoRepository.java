package dream.mongo.repository;

import dream.mongo.domain.dream;

import java.util.List;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<dream, String> {

    List<dream> findAll();

    List<dream> findByDreamRegex(String title);
}