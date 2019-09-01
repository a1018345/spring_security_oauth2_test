package lanyao.springsecurity.oauth.service;

import lanyao.springsecurity.oauth.domain.LanguageRepository;
import lanyao.springsecurity.oauth.domain.News;
import lanyao.springsecurity.oauth.domain.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    CommonService commonService;
    @Autowired
    LanguageRepository languageRepository;

    public News findByUuid(String newsUuid){
        News byUuid = newsRepository.findByUuid(newsUuid);
        return byUuid;
    }


    public News findById(Integer newsId){
        return newsRepository.findById(newsId).get();
    }

    public Integer countByLanguageAbbreviation(String languageAbbreviation){
        return newsRepository.countByNewsKindLanguageAbbreviation(languageAbbreviation);
    }

    public List<News> findByCarouselLink(String languageAbbreviation,Integer limited){
        Sort sort = new Sort(Sort.Direction.DESC, "news.releaseDate");
        PageRequest of = PageRequest.of(0, limited,sort);
        List<News> page=languageRepository.findAllByNewsKindLanguageAbbreviation(languageAbbreviation,of);
        return page;
    }


}
