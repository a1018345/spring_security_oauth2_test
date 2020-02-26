package lanyao.springsecurity.oauth.controller;

import lanyao.springsecurity.oauth.domain.News;
import lanyao.springsecurity.oauth.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;


//    @CrossOrigin
    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> findAll(
            OAuth2Authentication auth
    ){
        System.out.println("herere");
        List<News> news = new ArrayList<>();

        news.add(new News(1,"titleTest","content~~~~","3", LocalDateTime.now(),null));


        System.out.println("authauth:"+auth);

        return news;
    }






}
