package com.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* In this application, I am authenticating user with the active directory, If the user is valid then he will be
* able to access profile service if not the access will be restricted for him. Used LDAP protocol to get connect with
* the the active directory.  
*
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-04
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
