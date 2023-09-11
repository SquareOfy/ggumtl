package dream.card.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DreamCardRepository extends JpaRepository<DreamCard, Long> {

    @Query("select distinct d from DreamCard d join fetch d.dreamCardAuthor join fetch d.dreamCardOwner " +
            "join fetch d.dreamCardLike")
    List<DreamCard> findCardInfoByAll();

    @Query("select count(dcl) > 0 from DreamCardLike dcl " +
            "where dcl.dreamCard.dreamCardId = :dreamCardId and dcl.user.userId = :userId")
    boolean existLikeCardByUser(@Param("dreamCardId") long dreamCardId, @Param("userId") long userId);
}
