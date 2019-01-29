package com.liwh.config;

import com.liwh.properties.OAuth2ClientProperties;
import com.liwh.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

/**
 * @author: Liwh
 * @ClassName: DefultAuthorizationServerConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-25 11:31 AM
 */
@Configuration
@EnableAuthorizationServer
public class DefaultAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private TokenStore tokenStore;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;
    private final String SCOPES = "all";

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        /*通过TokenEnhanceChain改变security default token的生成规则*/
        if (jwtTokenEnhancer != null && jwtAccessTokenConverter != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            ArrayList<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtAccessTokenConverter);
            enhancers.add(jwtTokenEnhancer);
            tokenEnhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //client信息放在内存就行，我们又不是QQ支持任意第三方
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        OAuth2ClientProperties[] clientProperties = securityProperties.getOauth2().getClients();
        if (ArrayUtils.isNotEmpty(clientProperties)) {
            for (OAuth2ClientProperties client : clientProperties) {
                builder.withClient(client.getClientId())
                        .secret(client.getSecret())
                        .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(2592000)
                        .authorizedGrantTypes("refresh_token", "password", "authorization_code")
                        .scopes(SCOPES);
            }
        }

    }
}
