package com.ksquare.sso.repository;

import org.springframework.data.repository.CrudRepository;

import com.ksquare.sso.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>{
	
	UserRole findByName(String name);
	
}
