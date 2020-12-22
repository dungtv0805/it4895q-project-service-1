package com.it4895q.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

	private String password;

	private String username;
	
	private String firstName;
	
	private String lastName;
	
}
