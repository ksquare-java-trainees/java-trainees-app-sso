package com.ksquare.sso.service;

import java.util.List;

import com.ksquare.sso.domain.User;

public interface UserService {
	List<User> getUsers();
	User getUser(Long id);
	User getUser(String username);
	User addUser(User user);
	void deleteUser(String username);
	User updateUser(String username, User user);
	List<String> areUsers(List<String> usernames);
}
