package dream.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f " +
            "where f.fromUser.userId = :userId")
    List<Follow> getAllFollowingUsers(@Param("userId") long userId);

}
