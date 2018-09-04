package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;


/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-04
*/

@Configuration
@EnableWebSecurity
@Order(1)
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Value("${ad.domain}")
	private String AD_DOMAIN;

	@Value("${ad.url}")
	private String AD_URL;
		
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN,
				AD_URL);
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		return provider;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        	.antMatchers("/hello").permitAll()
        	.anyRequest().authenticated()
          .and()
          	.csrf().disable()
          	.httpBasic();    	
    }
}
