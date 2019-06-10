package com.ksquare.sso.service;

import java.util.List;

import com.ksquare.sso.domain.UserRole;

public interface UserRoleService {
	List<UserRole> getRoles();
	UserRole getRole(String name);
	UserRole addRole(UserRole role);
	void deleteRole(String name);
}
