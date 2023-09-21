package dream.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f " +
            "where f.fromUser.userId = :userId")
    List<Follow> getAllFollowingUsers(@Param("userId") long userId);

    @Query(value = "select exists (select 1 from follow "
            +"where follow.from_id = :userId and follow.to_id = :toId)", nativeQuery = true)
    int checkExistFollow(@Param("userId") long userId, @Param("toId") long toId);

    @Query("select f from Follow f where f.fromUser.userId=:userId and f.toUser.userId = :toId")
    Optional<Follow> findByFromIdAndToId(@Param("userId") long userId, @Param("toId") long toId);


}
