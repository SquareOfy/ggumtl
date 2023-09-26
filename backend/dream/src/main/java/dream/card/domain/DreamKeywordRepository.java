package dream.card.domain;

import dream.card.dto.request.RequestKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DreamKeywordRepository extends JpaRepository<DreamKeyword, Long> {

    @Query("select kw from DreamKeyword kw where kw.keyword in :keywords")
    List<DreamKeyword> findByKeywordIn(@Param("keywords")List<String> keywords);
}
