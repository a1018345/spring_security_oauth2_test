package lanyao.springsecurity.oauth.config.resource;


import lanyao.springsecurity.oauth.config.authorization.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {


    @Autowired
    @Qualifier(value = "resourceServerEnhancer")
    TokenEnhancer tokenEnhancer;

    @Autowired
    @Qualifier("resourceServerDefaultTokenService")
    ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    CustomAccessTokenConverter customAccessTokenConverter;

    @Autowired
    @Qualifier("resourceCustomAuthorizationEntryPoint")
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .anonymous().disable()
                .authorizeRequests()
                .mvcMatchers("/newsKind/**").permitAll()
                .anyRequest().authenticated()
                .mvcMatchers("/news/**").access("hasRole('ADMIN')");

        http.headers().frameOptions().sameOrigin();

        super.configure(http);
    }




    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenServices(resourceServerTokenServices)
                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }



//定義為authorization server跟resource server完全獨立，所以在declare同樣的物件

    @Bean("resourceServerDefaultTokenService")
    public ResourceServerTokenServices  tokenServices(@Autowired @Qualifier("authorizationServerTokenStore") TokenStore tokenStore) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);

        defaultTokenServices.setTokenEnhancer(tokenEnhancer);
        defaultTokenServices.setAccessTokenValiditySeconds(300);
        defaultTokenServices.setRefreshTokenValiditySeconds(60*60*24*30);

        return defaultTokenServices;
    }


//    @Bean("resourceServerTokenStore")
//    public TokenStore tokenStore(@Autowired @Qualifier("resourceServerAccessTokenConverter") JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }

    @Bean("resourceServerAccessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        //建議使用RSA的非對稱加密，resourceServer應該放public key

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        converter.setAccessTokenConverter(customAccessTokenConverter);
        return converter;
    }




    @Bean(value = "resourceServerEnhancer")
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    //自訂錯誤訊息
    @Bean(value = "resourceCustomAuthorizationEntryPoint")
    public CustomAuthenticationEntryPoint AuthenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }





}
