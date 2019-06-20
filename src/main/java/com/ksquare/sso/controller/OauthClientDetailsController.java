package com.ksquare.sso.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksquare.sso.domain.OauthClientDetails;
import com.ksquare.sso.service.OauthClientDetailsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/client-api")
public class OauthClientDetailsController {
	
	private static Logger logger = LoggerFactory.getLogger(OauthClientDetailsController.class);
	
	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;
	
	/**
	 * Returns the list of all API clients registered on the server.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getAllClientAPI",
			notes = "Returns the list of all API clients registered on the server",
			response = OauthClientDetails.class,
			responseContainer = "List")
	public ResponseEntity<?> getAllClientAPI(){
		List<OauthClientDetails> clients = oauthClientDetailsService.getAPIclients();
		logger.info("Listing all API clients");
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	/**
	 * Returns the API client information of the client with the id provided
	 * @param clientid
	 * @return
	 */
	@RequestMapping(value = "/{clientid}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "getClientAPI",
			notes = "Returns the API client information of the client with the id provided",
			response = OauthClientDetails.class)
	public ResponseEntity<?> getClientAPI(@PathVariable String clientid){
		OauthClientDetails client = oauthClientDetailsService.getAPIclient(clientid);
		logger.info("Returning API client of " + clientid);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	/**
	 * Adds a new API client with the API client information provided.
	 * Returns an HTTP CREATED status code.
	 * @param client
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "addClientAPI",
    		notes = "Adds a new API client with the API client information provided. Returns an HTTP CREATED status code",
    		response = OauthClientDetails.class)
	public ResponseEntity<?> addClientAPI(@RequestBody OauthClientDetails client){
		logger.info("Adding APLI client " + client.getId());
		OauthClientDetails newClient = oauthClientDetailsService.addAPIclient(client);
		return new ResponseEntity<>(newClient, HttpStatus.CREATED);
	}
	
	
	/**
     * Updates the API client info of the client with the clientid provided
     * @param clientid
     * @param client
     * @return
     */
    @RequestMapping(value = "/{clientid}", method = RequestMethod.PUT,
           produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "updateUser",
            notes = "Updates an API client info. Receives the clientid in the path and a Client object with the updated user info in the request body")
    public ResponseEntity<?> updateClientAPI(@PathVariable String clientid, @RequestBody OauthClientDetails client){
        logger.info("Updating API client " + client.getId());
        oauthClientDetailsService.updateAPIclient(clientid, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	/**
	 * Deletes the API client with the client id provided and returns an HTTP OK status code.
	 * @param clientid
	 * @return
	 */
	@RequestMapping(value = "/{clientid}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "deleteAPIclient",
    		notes = "Deletes the API client with the client id provided and returns an HTTP OK status code.")
	public ResponseEntity<?> deleteClientAPI(@PathVariable String clientid){
		oauthClientDetailsService.deleteAPIclient(clientid);
		logger.info("Delete API client " + clientid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
