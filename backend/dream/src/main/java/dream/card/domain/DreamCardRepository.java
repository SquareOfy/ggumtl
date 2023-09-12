package dream.card.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DreamCardRepository extends JpaRepository<DreamCard, Long> {

    @Query("select distinct d from DreamCard d left join fetch d.dreamCardAuthor " +
            "left join fetch d.dreamCardOwner " +
            "left join fetch d.dreamCardLike")
    List<DreamCard> findCardInfoByAll(PageRequest pageRequest);

    @Query("select count(dcl) > 0 from DreamCardLike dcl " +
            "where dcl.dreamCard.dreamCardId = :dreamCardId and dcl.user.userId = :userId")
    boolean existLikeCardByUser(@Param("dreamCardId") long dreamCardId, @Param("userId") long userId);

    @Query("select distinct d from DreamCard d " +
            "left join fetch d.dreamCardOwner " +
            "left join d.dreamCardAuthor " +
            "left join fetch d.cardKeyword dc " +
            "left join fetch dc.keyWordId  " +
            "where d.dreamCardId = :id")
    Optional<DreamCard> findDetailsById(@Param("id") Long id);
}
