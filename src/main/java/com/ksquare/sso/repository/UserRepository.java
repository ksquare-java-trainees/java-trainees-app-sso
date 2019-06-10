package com.ksquare.sso.repository;

import org.springframework.data.repository.CrudRepository;

import com.ksquare.sso.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
}
