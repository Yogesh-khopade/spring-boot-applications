package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-06-26
*/

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
	    authManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
	 }
	
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
        	.authorizeRequests()
        	.antMatchers("/login").permitAll()
        	.anyRequest().authenticated()
          .and()
          	.csrf()
          	.disable();*/
    	
    	http.cors().and()
        .csrf().disable();
http.authorizeRequests().antMatchers("/login").permitAll();
http.anonymous().disable().authorizeRequests().antMatchers("/profile").authenticated();
    }
}
