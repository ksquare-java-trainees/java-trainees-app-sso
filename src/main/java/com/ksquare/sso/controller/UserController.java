package com.ksquare.sso.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserRole;
//import com.ksquare.sso.security.CrmUserDetails;
import com.ksquare.sso.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/api/users")
@Api(tags = {"user-endpoints"})
@SwaggerDefinition(tags = {
    @Tag(name = "Swagger Resource", description = "Usage documentation for the ksquare-sso api")
})
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getUsers() {

		/**
		 * Obtaining information about the current user
		 */
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// CrmUserDetails principal = (CrmUserDetails) authentication.getPrincipal();
		// logger.info("Logged in user name: " + principal.getUsername());

		List<User> users = userService.getUsers();
		logger.info("Listing all users");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getUser(@PathVariable String username) {
		User user = userService.getUser(username);
		logger.info("Returning client info of " + username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("USER")));
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> addAdminUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("ADMIN"), new UserRole("USER")));
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
		logger.info("Updated user info of " + username);
		userService.updateUser(username, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		logger.info("Deleted user " + username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	@ApiOperation(value = "Validate if a user is registered",
    notes = "The specified id is used to retrieve and return the diary entry",
    response = HttpStatus.class)
	public ResponseEntity<?> authUser() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/areUsers", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves a String list with names of unregistered users",
    response = String.class,
    responseContainer = "List")
	public ResponseEntity<?> areUsers(@RequestBody List<String> userNames) {
		List<User> users = userService.getUsers();
		for (User user : users) {
			loop: for(int i = 0; i<userNames.size(); i++) {
				if (user.getUsername().equals(userNames.get(i))) {
					userNames.remove(userNames.get(i));
					break loop;
				}
			} 
		}
		return new ResponseEntity<>(userNames, HttpStatus.OK);
	}

}
