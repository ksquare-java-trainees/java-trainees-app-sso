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

	@RequestMapping(method = RequestMethod.GET,
            produces = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getUsers",
	notes = "Gets the info of all the users. Returns a list with all the users info",
    response = User.class,
    responseContainer = "List")
	public ResponseEntity<?> getUsers() {
		List<User> users = userService.getUsers();
		logger.info("Listing all users");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET,
            produces = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getUser",
	notes = "Gets the info of a user. Receives the user name in the path and returns the correspondent info",
    response = User.class)
	public ResponseEntity<?> getUser(@PathVariable String username) {
		User user = userService.getUser(username);
		logger.info("Returning client info of " + username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "addUser",
	notes = "Creates a new user. Receives a User object with the user info in the request body",
    response = User.class)
	public ResponseEntity<?> addUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("USER")));
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "addAdminUser",
	notes = "Creates a new user with admin privileges. Receives a User object with the user info in the request body",
    response = User.class)
	public ResponseEntity<?> addAdminUser(@RequestBody User user) {
		logger.info("Adding user " + user.getUsername());
		user.setRoles(Arrays.asList(new UserRole("ADMIN"), new UserRole("USER")));
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.PUT,
            produces = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "updateUser",
	notes = "Updates a user info. Receives the username in the path and a User object with the updated user info in the request body")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
		logger.info("Updated user info of " + username);
		userService.updateUser(username, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE,
            produces = {"application/json"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "deleteUser",
	notes = "Deletes a user. Receives the name of the user to delete")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		logger.info("Deleted user " + username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET,
            produces = {"application/json"})
	@ApiOperation(value = "authUser",
	notes = "Validate if a user is registered",
    response = HttpStatus.class)
	public ResponseEntity<?> authUser() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/areUsers", method = RequestMethod.GET,
            consumes = {"application/json"},
            produces = {"application/json"})
	@ApiOperation(value = "areUsers",
	notes = "Determines if a bunch of people are real users. Receives a String list with the names of posible users and retrieves a String list with the names that"
			+ " are not real users",
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
