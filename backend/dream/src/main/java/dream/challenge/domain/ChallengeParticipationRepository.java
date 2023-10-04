package dream.challenge.domain;

import dream.card.domain.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long> {

    @Query("select c from ChallengeParticipation  c " +
            "where c.challenge.challengeId = :challengeId " +
            "and c.user.userId = :userId")
    Optional<ChallengeParticipation> getChallengeParticipationListByUserAndChallenge(
            @Param("userId") Long userId, @Param("challengeId") Long challengeId);


    @Query("select c from ChallengeParticipation  c " +
            "where c.challenge.challengeId = :challengeId " +
            "and c.user.userId = :userId " +
            "and c.isIn = :status ")
    Optional<ChallengeParticipation> getChallengeParticipationListByUserAndChallengeAndStatus(
            @Param("userId") Long userId, @Param("challengeId") Long challengeId, @Param("status") ChallengeStatus status);


    @Query("select c from ChallengeParticipation  c " +
            "where c.user.userId = :userId " +
            "and c.isIn = :status")
    List<ChallengeParticipation> getChallengeParticipationListByUserAndStatus(
            @Param("userId") Long userId, @Param("status") ChallengeStatus status);


    @Query("select c from ChallengeParticipation c " +
            "where c.user.userId = :userId " +
            "and c.challenge.challengeId = :challengeId " +
            "order by c.challengeParticipationId desc ")
    List<ChallengeParticipation> findRecentCertainChallenge(@Param("userId") Long userId, @Param("challengeId") Long challengeId);
}
