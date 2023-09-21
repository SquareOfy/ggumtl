package dream.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Long> {

    @Query("select c from ChallengeDetail  c " +
            "where c.challenge.challengeId = :challengeId " +
            "and c.user.userId = :userId " +
            "order by c.challengeDetailId desc ")
    Optional<List<ChallengeDetail>> getChallengeDetails(Long challengeId, Long userId);
}
