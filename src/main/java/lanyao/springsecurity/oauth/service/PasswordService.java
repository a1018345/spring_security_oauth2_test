package lanyao.springsecurity.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    PasswordEncoder passwordEncoder;



    public String encode(String str){

        String encode = passwordEncoder.encode(str);
        return encode;
    }



}
