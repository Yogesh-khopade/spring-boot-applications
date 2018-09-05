package com.config;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.scope-read}")
	private String scopeRead;

	@Value("${security.jwt.scope-write}")
	private String scopeWrite = "write";
	
	@Value("${security.jwt.auth-token-validity}")
	private int authTokenValidity;
	
	@Value("${security.jwt.refresh-token-validity}")
	private int refreshTokenValidity;

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;
	
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	 
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                   .checkTokenAccess("isAuthenticated()");
    }
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes("password","refresh_token","authorization_code","client_credentials")
                .scopes(scopeRead,scopeWrite)
                .autoApprove(true)
                //.resourceIds(resourceIds)
        		.accessTokenValiditySeconds(authTokenValidity)
        		.refreshTokenValiditySeconds(refreshTokenValidity);       
    } 
    
    @Autowired
	private JwtAccessTokenConverter accessTokenConverter;

    @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		
    	endpoints.tokenStore(tokenStore)
    	.accessTokenConverter(accessTokenConverter)
    	.tokenEnhancer(enhancerChain)
        .authenticationManager(authenticationManager);
	}
}
