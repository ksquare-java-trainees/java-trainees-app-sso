package com.ksquare.sso.repository;

import org.springframework.data.repository.CrudRepository;

import com.ksquare.sso.domain.OauthClientDetails;

public interface OauthClientDetailsRepository extends CrudRepository<OauthClientDetails, String>{

}
