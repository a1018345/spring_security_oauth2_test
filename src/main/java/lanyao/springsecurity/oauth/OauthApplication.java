package lanyao.springsecurity.oauth;

import org.apache.commons.codec.binary.Base32;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }






    @Bean
    public Base32 urlBase32(){
        return new Base32();
    }





}
