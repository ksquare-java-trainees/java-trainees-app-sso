package com.ksquare.sso.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsers() {
		return Lists.newArrayList(userRepository.findAll());
	}
	
	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(String username) {
		userRepository.delete(userRepository.findByUsername(username));		
	}

	@Override
	public User updateUser(String username, User user) {
		User userToUpdate = userRepository.findByUsername(username);
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
		userToUpdate.setRoles(user.getRoles());
		return userRepository.save(userToUpdate);
	}
	
	@Override
	public List<String> areUsers(List<String> usernames){
		List<User> existingUsers = Lists.newArrayList(userRepository.findAll());
		for (User user : existingUsers) {
			if(usernames.contains(user.getUsername())) {
				usernames.remove(user.getUsername());
			}
		}
		return usernames;
	}
	
	@PostConstruct
	private void setupDefaultUser() {
		if (userRepository.count() == 0) {
			userRepository.save(new User(
					"crmadmin", 
					passwordEncoder.encode("adminpass"), 
					Arrays.asList(
							new UserRole("USER"), 
							new UserRole("ADMIN"))));
		}		
	}
	
}
