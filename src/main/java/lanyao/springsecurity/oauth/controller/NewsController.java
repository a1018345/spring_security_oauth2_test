package lanyao.springsecurity.oauth.controller;

import lanyao.springsecurity.oauth.domain.News;
import lanyao.springsecurity.oauth.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;



    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> findAll(
            OAuth2Authentication auth
    ){
        System.out.println("herere");
        List<News> news = new ArrayList<>();


        System.out.println("authauth:"+auth);

        return news;
    }






}
