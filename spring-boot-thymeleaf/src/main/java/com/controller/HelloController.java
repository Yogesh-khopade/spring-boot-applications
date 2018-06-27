package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* The Themeleaf program implements an application that
* has default index page and welcome page for users.
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
