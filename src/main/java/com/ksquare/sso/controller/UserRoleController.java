package com.ksquare.sso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.service.UserRoleService;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoles() {
		List<UserRole> roles = userRoleService.getRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getRole(@PathVariable String name) {
		UserRole role = userRoleService.getRole(name);
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

}
