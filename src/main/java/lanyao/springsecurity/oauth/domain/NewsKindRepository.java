package lanyao.springsecurity.oauth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsKindRepository extends JpaRepository<NewsKind,Integer>, JpaSpecificationExecutor<NewsKind> {

    @Query(value = "select nk from NewsKind nk where nk.language.id = ?1 order by nk.kindOrder")
    List<NewsKind> findByLanguage(Integer language);

    @Query(value = "select nk from NewsKind nk where nk.language.abbreviation = ?1 order by nk.kindOrder")
    List<NewsKind> findByLanguageAbbreviation(String languageAbbreviation);

    @Query("select nk from NewsKind nk where nk.uuid = function('unhex',?1) ")
    NewsKind findByUuid(String uuid);


    List<NewsKind> findByLanguageIdOrderByKindOrder(Integer languageId);




}
