package dream.auction.domain;

import dream.challenge.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Challenge, Long> {
}
