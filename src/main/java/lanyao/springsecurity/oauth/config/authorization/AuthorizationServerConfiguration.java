package lanyao.springsecurity.oauth.config.authorization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("authorizationServerPasswordEncoder")
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    @Qualifier(value = "authorizationServerEnhancer")
    TokenEnhancer tokenEnhancer;


//    @Autowired
//    @Qualifier("authorizationServerTokenServices")
//    DefaultTokenServices defaultTokenServices;

    @Autowired
    @Qualifier("authorizationServerTokenStore")
    TokenStore tokenStore;



    @Autowired
    @Qualifier("authorizationCustomWebResponseExceptionTranslator")
    CustomWebResponseExceptionTranslator customAuthenticationException;


    @Autowired
    @Qualifier("authorizationServerAccessTokenConverter")
    JwtAccessTokenConverter jwtAccessTokenConverter;

    private static String REALM="CRM_REALM";
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置client端

        String password = passwordEncoder.encode("secret");

        clients
                .inMemory()
                //client_id
                .withClient("client_id")
                //cloent_secret
                .secret(password)
                //該client允許的授權類型
                .authorizedGrantTypes("password", "refresh_token")
                //用戶允許應用程式代表他們做的操作
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(300)
                .refreshTokenValiditySeconds(60*60*24*30);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);

    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        //tokenEnhancer是為了在token增加自定義的屬性
        //JwtAccessTokenConverter說明token如何解析
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));




        //指定token儲存位置
        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                //指定認證管理器
                .authenticationManager(authenticationManager)
                //refresh token使用
                .userDetailsService(customUserDetailsService)
                .exceptionTranslator(customAuthenticationException);
//                .tokenServices(defaultTokenServices);


    }


//取使用者帳號密碼
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
//                .withUser("admin")
//                .password(password)
//                .roles("Admin");
    }







    @Bean(value = "authorizationServerEnhancer")
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    @Bean("authorizationServerAccessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //對稱加密，所以在resource server也要
        converter.setSigningKey("123");

//        建議使用RSA的非對稱加密，authorizationServer應該放private key

        return converter;
    }

//    @Primary
//    @Bean("authorizationServerTokenServices")
//    public DefaultTokenServices tokenServices(
//            @Autowired @Qualifier("authorizationServerTokenStore") TokenStore tokenStore
//    ) {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//
//        defaultTokenServices.setTokenStore(tokenStore);
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }

    @Bean("authorizationServerTokenStore")
    public TokenStore tokenStore(
            @Autowired @Qualifier("authorizationServerAccessTokenConverter") JwtAccessTokenConverter jwtAccessTokenConverter
    ) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean(value = "authorizationServerPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    //自訂BadCredentialsException、UserNameNotFound訊息
    @Bean(value = "authorizationCustomWebResponseExceptionTranslator")
    public CustomWebResponseExceptionTranslator authorizationEntryPoint(){
        return new CustomWebResponseExceptionTranslator();
    }







}
