package dream.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<comment, Long> {
}
