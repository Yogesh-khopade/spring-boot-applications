package com.controller;

/**
 * In this application, I have provided in memory authentication. If user is valid then he will be able to get user
 * data, else there will be no access to him. I have used in memory SQL database and I am reading this data usig JPA.
 * With the use of domain class, data will be accessible to the user. At the end rest controller will convert it 
 * into JSON while responding it to the user.
 *   
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.User;
import com.repository.UserRepository;

@RestController
@Controller
public class ResourceController {

	@Autowired
	UserRepository userRepository;
		
    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    
    @RequestMapping( value = "/demoservice", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User demoGraphic(@RequestParam("cust_id") String custId) {    	
    	
    	return userRepository.findByCust_Id(custId);
    }
}
