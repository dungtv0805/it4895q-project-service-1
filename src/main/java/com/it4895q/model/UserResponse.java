package com.it4895q.model;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;

	private String username;

	private String firstName;

	private String lastName;

	private String fullName;

	private String linkAvatar;
	
	private String token;

	public UserResponse() {

	}

	public UserResponse(Long id, String username, String firstName, String lastName, String linkAvatar, String token) {
		this.id = id;
		this.firstName = firstName;
		this.username = username;
		this.lastName = lastName;
		this.fullName = firstName + " " + lastName;
		this.linkAvatar = linkAvatar;
		this.username = username;
		this.token = token;
	}
}
