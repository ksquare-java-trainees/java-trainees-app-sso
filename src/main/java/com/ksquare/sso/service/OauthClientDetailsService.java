package com.ksquare.sso.service;

import java.util.List;

import com.ksquare.sso.domain.OauthClientDetails;

public interface OauthClientDetailsService {
	List<OauthClientDetails> getAPIclients();
	OauthClientDetails getAPIclient(String id);
	OauthClientDetails addAPIclient(OauthClientDetails client);
	OauthClientDetails updateAPIclient(String id, OauthClientDetails client);
	void deleteAPIclient(String id);
}
