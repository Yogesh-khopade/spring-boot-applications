package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-06-26
*/

@RestController
@RequestMapping("/")
public class HelloController {

	@RequestMapping("/hello")
	public String hello(){
		return "Hello World..!";
	}
}
