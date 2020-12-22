package com.it4895q.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class JwtRequest {
	private String token;

	@JsonCreator
	public JwtRequest(@JsonProperty("token") String token) {
		this.token = token;
	}
}
