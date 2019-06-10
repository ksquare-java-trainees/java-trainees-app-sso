package com.ksquare.sso.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.service.SignupService;

@RestController
public class SignupController {
	
	@Autowired
	private SignupService signupService;

    /**
     * 
     * user signup
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
   		user.setRoles(Arrays.asList(new UserRole("USER")));
    	signupService.addUser(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
