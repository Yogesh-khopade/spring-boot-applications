package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* In this applicaton, I am trying to give basic security to access the profile page.
* Basic In-memory authentication is implemented.
* 
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-06-27
*/

@Controller
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/hello")
	public String hello(){
		return "welcome";
	}
}
