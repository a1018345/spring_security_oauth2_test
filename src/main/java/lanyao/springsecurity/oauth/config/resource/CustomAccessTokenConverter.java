package lanyao.springsecurity.oauth.config.resource;

import lanyao.springsecurity.oauth.config.OAuthUser;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        OAuthUser oAuthUser = new OAuthUser(authentication.getOAuth2Request(), authentication.getUserAuthentication());
        return oAuthUser;


    }
}
