package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* This program implements an application that
* has inmemory authentication for user and welcome page for authorized users.
*
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-06-27
*/

@Controller
public class HelloController {

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/profile")
	public String profile(){
		return "profile";
	}
}
