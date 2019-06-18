package com.ksquare.sso.domain;

import javax.persistence.*;

@Entity
@Table(name="ROLE")
public class UserRole {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;	

	UserRole() { 
	}

	public UserRole(String name) { 
		this.name = name; 
	} 
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() { 
		return name; 
	} 

	public void setName(String name) { 
		this.name = name; 
	} 
}
