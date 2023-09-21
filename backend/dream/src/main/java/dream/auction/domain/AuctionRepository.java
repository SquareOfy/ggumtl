package dream.auction.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("select a from Auction a join fetch a.dreamCard ad " +
            "join fetch ad.cardKeyword ac " +
            "join fetch ac.keyWordId " +
            "where a.auctionId = :id")
    Optional<Auction> findAuctionDetailById(@Param("id") Long id);

    @Query("select a from Auction a join fetch a.bidding b " +
            "join fetch b.user " +
            "join fetch a.dreamCard " +
            "where a.auctionId = :id " +
            "and a.endedAt < :now " +
            "order by a.auctionId desc")
    Optional<Auction> findBiddingById(@Param("id") Long id, @Param("now") LocalDateTime now);

}
