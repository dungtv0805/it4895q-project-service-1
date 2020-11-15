package com.it4895q.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
	private String username;

	private String password;

	private String phone;
}
