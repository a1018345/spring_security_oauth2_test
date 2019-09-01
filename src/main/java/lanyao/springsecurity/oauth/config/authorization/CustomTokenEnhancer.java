package lanyao.springsecurity.oauth.config.authorization;

import lanyao.springsecurity.oauth.config.UserSession;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

//        Authentication userAuthentication = authentication.getUserAuthentication();
        //看想要把甚麼放進JWT中

        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);

            UserSession userSession = (UserSession)authentication.getPrincipal();
            Map<String, Object> additionalInfo = new HashMap<>();
            //在fresh_token的時候spring security 自動取user_name使用loadUserByUsername()，故先使用覆蓋的方式
            additionalInfo.put("user_name", userSession.getEmail());

            additionalInfo.put("name",userSession.getName());


            token.setAdditionalInformation(additionalInfo);


            return token;
        }
        return accessToken;
    }
}
