package com.config;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.inMemoryAuthentication().withUser("rm@yes.in").password("pass@123").roles("RM");
		authManagerBuilder.inMemoryAuthentication().withUser("cc@yes.in").password("pass@123").roles("CC");
		authManagerBuilder.inMemoryAuthentication().withUser("pm@yes.in").password("pass@123").roles("PM");
		authManagerBuilder.inMemoryAuthentication().withUser("sm@yes.in").password("pass@123").roles("SM");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
