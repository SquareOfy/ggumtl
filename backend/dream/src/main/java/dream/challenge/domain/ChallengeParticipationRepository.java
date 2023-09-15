package dream.challenge.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long> {

    @Query("select c from ChallengeParticipation  c " +
            "where c.challenge.challengeId = :challengeId " +
            "and c.user.userId = :userId")
    Optional<ChallengeParticipation> getChallengeParticipationListByUserAndChallenge(
            @Param("userId") Long userId, @Param("challengeId") Long challengeId);

}
