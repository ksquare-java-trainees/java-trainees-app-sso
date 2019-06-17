package com.ksquare.sso.domain;

import javax.persistence.*;

@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails {

	@Id
	@Column(name="client_id")
	private String id;
	
	@Column(name="resource_ids")
	private String resourceId;
	
	@Column(name="client_secret")
	private String secret;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="authorized_grant_types")
	private String grantTypes;
	
	@Column(name="web_server_redirect_uri")
	private String redirectUri;
	
	@Column(name="authorities")
	private String authorities;
	
	@Column(name="access_token_validity")
	private int accessTokenValidity;
	
	@Column(name="refresh_token_validity")
	private int refreshTokenValidity;
	
	@Column(name="additional_information")
	private String additionalInfo;
	
	@Column(name="autoapprove")
	private String autoapprove;
	
	public OauthClientDetails() {
		
	}
	
	public OauthClientDetails(String id, String resourceId, String secret, String scope, String grantTypes,
			String redirectUri, String authorities, int accessTokenValidity, int refreshTokenValidity,
			String additionalInfo, String autoapprove) {
		this.id = id;
		this.resourceId = resourceId;
		this.secret = secret;
		this.scope = scope;
		this.grantTypes = grantTypes;
		this.redirectUri = redirectUri;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInfo = additionalInfo;
		this.autoapprove = autoapprove;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}	
}
