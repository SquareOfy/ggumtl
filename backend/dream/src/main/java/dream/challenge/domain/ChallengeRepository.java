package dream.challenge.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select distinct c from Challenge  c " +
            "left join fetch c.keywords k " +
            "left join fetch k.keyword " +
            "where c.challengeId = :id")
    Optional<Challenge> findChallengeKeyword(@Param("id") Long id);

    @Query("select distinct c from Challenge  c " +
            "left join fetch c.challengeParticipations " +
            "where c.challengeId = :id")
    Optional<Challenge> findChallengeParticipates(@Param("id") Long id);

    @Query("select distinct c from Challenge c " +
            "join fetch c.keywords ck " +
            "join fetch ck.keyword cdk " +
            "where cdk.keyword in :keywords " +
            "order by c.hits desc ")
    List<Challenge> findRecommendChallengeByDreamCard(@Param("keywords") List<String> keywords);


}
