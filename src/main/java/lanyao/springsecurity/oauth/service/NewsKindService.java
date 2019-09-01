package lanyao.springsecurity.oauth.service;


import lanyao.springsecurity.oauth.domain.NewsKind;
import lanyao.springsecurity.oauth.domain.NewsKindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsKindService {

    @Autowired
    NewsKindRepository newsKindRepository;




    public List<NewsKind> findAll(Integer languageId){

        List<NewsKind> byLanguage = newsKindRepository.findByLanguage(languageId);

        return byLanguage;
    }



}
