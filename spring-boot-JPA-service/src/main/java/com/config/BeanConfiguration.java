package com.config;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class BeanConfiguration extends ResourceServerConfigurerAdapter {
	
	@Value("${security.signing-key}")
	private String signingKey;
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
        	.authorizeRequests()
        	.antMatchers("/","/dashboard","/runtime.js","/polyfills.js","/styles.js","/scripts.js","/vendor.js","/main.js","/assets/**").permitAll()
        	.anyRequest().authenticated()
        .and()
        	.csrf()
        	.disable();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}
	
	@Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

   /* @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new LdapShaPasswordEncoder();
		return encoder;
	}
}
