package dream.common.scheduler;


import dream.challenge.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private final ChallengeParticipationRepository challengeParticipationRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    @Transactional
    @Scheduled(cron = "0 59 23 * * *")
    public void updateChallengeParticipationIsIn(){

        List<ChallengeParticipation> participations = challengeParticipationRepository.getChallengeParticipationByIsIn(ChallengeStatus.P);

        log.info("participations size :{}", participations.size());

        if(participations.size()==0) return;
        for (ChallengeParticipation participation : participations) {
            log.info("participationId :{}", participation.getChallengeParticipationId());
            Challenge challenge = challengeRepository.findById(participation.getChallenge().getChallengeId()).orElse(null);

            if (challenge == null) continue;
            int period = Integer.parseInt(challenge.getPeriod().replace("일", ""));
            log.info("period : {}", period);
            LocalDate expiryDate = participation.getCreatedAt().toLocalDate().plusDays(period);
            log.info("expiredDate : {}", expiryDate.toString());
            if (LocalDate.now().isAfter(expiryDate)) {
                List<ChallengeDetail> recentChallengeDetails = challengeDetailRepository.findTopByUserIdAndChallengeIdOrderByCreatedAtDesc(participation.getUser().getUserId(), participation.getChallenge().getChallengeId());

                log.info("userId: {}", participation.getUser().getUserId());
                log.info("challengeId : {}", participation.getChallenge().getChallengeId());
                log.info("recentChallengeDetails.size() : {}", recentChallengeDetails.size());
                if (!recentChallengeDetails.isEmpty()) {
                    ChallengeDetail recentChallengeDetail = recentChallengeDetails.get(0);
                    log.info("recentChallengeDetailId :{}", recentChallengeDetail.getChallengeDetailId());

                    if (recentChallengeDetail.getCreatedAt().toLocalDate().isAfter(participation.getCreatedAt().toLocalDate()) &&
                            recentChallengeDetail.getCreatedAt().toLocalDate().isBefore(expiryDate) && recentChallengeDetail.getMaxDays() == period) {
                        participation.updateIsIn(ChallengeStatus.S);
                        continue;
                    }
                }

                  participation.updateIsIn(ChallengeStatus.F);


            }
        }
    }
}
