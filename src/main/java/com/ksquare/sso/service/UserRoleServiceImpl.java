package com.ksquare.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.repository.UserRoleRepository;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserRole> getRoles() {
		return Lists.newArrayList(userRoleRepository.findAll());
	}

	@Override
	public UserRole getRole(String name) {
		return userRoleRepository.findByName(name);
	}

	@Override
	public UserRole addRole(UserRole role) {
		return userRoleRepository.save(role);
	}

	@Override
	public void deleteRole(String name) {
		userRoleRepository.delete(userRoleRepository.findByName(name));		
	}

}
