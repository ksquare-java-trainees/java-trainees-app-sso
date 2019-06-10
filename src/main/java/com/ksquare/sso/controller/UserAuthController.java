package com.ksquare.sso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/islogged")
public class UserAuthController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?>  isLogged() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
