package com.ksquare.sso.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ksquare.sso.domain.OauthClientDetails;
import com.ksquare.sso.repository.OauthClientDetailsRepository;

@Service("oauthClientDetailsService")
@Transactional
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
	
	private static final int PASS_SIZE = 100;
	
	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepository;
	
	private RandomStringUtils randomStringUtils;
	
	public OauthClientDetailsServiceImpl() {
		randomStringUtils = new RandomStringUtils();
	}

	@Override
	public List<OauthClientDetails> getAPIclients() {
		return Lists.newArrayList(oauthClientDetailsRepository.findAll());
	}

	@Override
	public OauthClientDetails getAPIclient(String id) {
		return oauthClientDetailsRepository.findOne(id);
	}

	@SuppressWarnings("static-access")
	@Override
	public OauthClientDetails addAPIclient(OauthClientDetails client) {
		client.setSecret(randomStringUtils.randomAlphabetic(PASS_SIZE));
		return oauthClientDetailsRepository.save(client);
	}

	@SuppressWarnings("static-access")
	@Override
	public OauthClientDetails updateAPIclient(String id, OauthClientDetails client) {
		OauthClientDetails updatedClient =  oauthClientDetailsRepository.findOne(id);
		updatedClient.setId(client.getId());
		updatedClient.setResourceId(client.getResourceId());
		updatedClient.setSecret(randomStringUtils.randomAlphabetic(PASS_SIZE));
		updatedClient.setScope(client.getScope());
		updatedClient.setGrantTypes(client.getGrantTypes());
		updatedClient.setRedirectUri(client.getRedirectUri());
		updatedClient.setAuthorities(client.getAuthorities());
		updatedClient.setAccessTokenValidity(client.getAccessTokenValidity());
		updatedClient.setRefreshTokenValidity(client.getRefreshTokenValidity());
		updatedClient.setAdditionalInfo(client.getAdditionalInfo());
		updatedClient.setAutoapprove(client.getAutoapprove());
		return oauthClientDetailsRepository.save(updatedClient);
	}

	@Override
	public void deleteAPIclient(String id) {
		oauthClientDetailsRepository.delete(oauthClientDetailsRepository.findOne(id));
		
	}
	
	@PostConstruct
	private void setupDefaultAPIclients() {
		if (oauthClientDetailsRepository.count() > 0)
			return;
		
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"crmClient1",
				null,
				"crmSuperSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"chatId",
				null,
				"chatSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
		oauthClientDetailsRepository.save(new OauthClientDetails(
				"calendarId",
				null,
				"calendarSecret",
				"read,write,trust",
				"password,refresh_token",
				null,
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT",
				7776000, 2592000,
				null, null));
	}

}
