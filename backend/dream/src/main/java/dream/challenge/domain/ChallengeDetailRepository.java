package dream.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Long> {

    @Query("select c from ChallengeDetail  c " +
            "where c.challenge.challengeId = :challengeId " +
            "and c.user.userId = :userId " +
            "order by c.challengeDetailId desc ")
    Optional<List<ChallengeDetail>> getChallengeDetails(Long challengeId, Long userId);

    @Query("select cd from ChallengeDetail cd " +
            "where cd.user.userId = :userId and cd.challenge.challengeId = :challengeId " +
            "order by cd.createdAt desc")
    List<ChallengeDetail>  findTopByUserIdAndChallengeIdOrderByCreatedAtDesc(@Param("userId") Long userId, @Param("challengeId") Long challengeId);
}
