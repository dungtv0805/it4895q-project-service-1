package com.it4895q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it4895q.entity.User;
import com.it4895q.model.RegisterRequest;
import com.it4895q.model.RegisterResponse;
import com.it4895q.security.JwtTokenUtil;
import com.it4895q.service.CustomUserDetailsService;
import com.it4895q.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class RegisterController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody RegisterRequest user) throws Exception {
		User userExsited = userService.findByUserName(user.getUsername());
		RegisterResponse response = new RegisterResponse();
		if (userExsited != null) {
			response.setMessage("User_Exsited");
			return ResponseEntity.ok(response);
		}

		userService.save(user);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		userService.updateToken(user.getUsername(), token);

		response.setMessage("successfully");
		return ResponseEntity.ok(response);
	}

}
