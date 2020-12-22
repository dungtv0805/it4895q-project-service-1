package com.it4895q.model;

import java.util.ArrayList;
import java.util.List;

import com.it4895q.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

//	private String firstName;
//
//	private String lastName;
//
//	private String linkAvatar;

	private String token;

	private String message;

}
