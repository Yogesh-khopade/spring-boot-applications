package com.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* In this application, I am authenticating user with the active directory, If the user is valid then Authorization 
* server will generate token and user will be able to get the token. If user not valid then token won't be generated.
* Once user got the token, he can use this token to access further services. Resource server checks the validity 
* of the token every time.
* 
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-05
*/

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String login(){
		return "hello";
	}
	
	@RequestMapping("/profile")
	public String profile(Principal principal){
		return "Hello, " + principal.getName();
	}
}
