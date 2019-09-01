package lanyao.springsecurity.oauth.controller;


import com.fasterxml.jackson.annotation.JsonView;
import lanyao.springsecurity.oauth.View;
import lanyao.springsecurity.oauth.domain.NewsKind;
import lanyao.springsecurity.oauth.service.NewsKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("newsKind")
public class NewsKindController {



    @Autowired
    NewsKindService newsKindService;



    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @JsonView({View.NewsKind.class})
    public List<NewsKind> findAll(){
        System.out.println("hererssssse");
        List<NewsKind> newsKinds = new ArrayList<>();


        newsKinds = newsKindService.findAll(1);
        return newsKinds;
    }


}
