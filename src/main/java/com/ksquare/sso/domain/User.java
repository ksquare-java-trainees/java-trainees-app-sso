package com.ksquare.sso.domain;

import java.util.List;

import javax.persistence.*;

@Entity 
@Table(name = "USERS")
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable( name = "users_role",
				joinColumns = { @JoinColumn(name = "user_id") },	
				inverseJoinColumns = { @JoinColumn(name = "role_id") } )
	private List<UserRole> roles; 

	User() { 
	} 

	public User(String username, String password, List<UserRole> roles) { 
		this.username = username; 
		this.password = password; 
		this.roles = roles; 
	} 

	public String getUsername() { 
		return username; 
	} 

	public void setUsername(String username) { 
		this.username = username; 
	} 

	public String getPassword() { 
		return password; 
	} 

	public void setPassword(String password) { 
		this.password = password; 
	} 

	public List<UserRole> getRoles() { 
		return roles; 
	} 

	public void setRoles(List<UserRole> roles) { 
		this.roles = roles; 
	} 

}
