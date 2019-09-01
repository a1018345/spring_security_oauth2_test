package lanyao.springsecurity.oauth.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {





    @Query("select news from Language l inner join l.newsKinds nk inner join nk.news news where l.abbreviation = :languageAbbreviation")
    List<News> findAllByNewsKindLanguageAbbreviation(@Param("languageAbbreviation") String languageAbbreviation, Pageable pageable);

    Language findByAbbreviation(String abbreviation);


}
