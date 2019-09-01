package lanyao.springsecurity.oauth.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer>, JpaSpecificationExecutor<News> {




    @Query("select n from News n where n.uuid = function('unhex',?1) ")
    News findByUuid(String uuid);


    Integer countByNewsKindLanguageAbbreviation(String abbreviation);

    Page<News> findAllByNewsKindLanguageAbbreviation(String languageAbbreviation, Pageable pageable);

    List<News> findTop5ByNewsKindIdAndStatusOrderByReleaseDateDesc(Integer newsKindId, String status);
    List<News> findTop5ByNewsKindIdInOrderByReleaseDateDesc(List<Integer> newsKindsId);




}
