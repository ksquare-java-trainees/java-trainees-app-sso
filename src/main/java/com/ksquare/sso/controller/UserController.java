package com.ksquare.sso.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.security.CrmUserDetails;
import com.ksquare.sso.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getClients() {
		
		/**
		 * Obtaining information about the current user
		 */
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //CrmUserDetails principal = (CrmUserDetails) authentication.getPrincipal();
        //logger.info("Logged in user name: " + principal.getUsername());
		
		List<User> users = userService.getUsers();
		logger.info("Listing all users");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getUser(@PathVariable String username){
		User user = userService.getUser(username);
		logger.info("Returning client info of " + username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> addUser(@RequestBody User user){
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("USER")));
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user){
		logger.info("Updated user info of " + username);
		userService.updateUser(username, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable String username){
		userService.deleteUser(username);
		logger.info("Deleted user " + username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
}
