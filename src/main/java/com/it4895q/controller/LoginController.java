package com.it4895q.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it4895q.entity.User;
import com.it4895q.model.AuthenticationResponse;
import com.it4895q.model.JwtRequest;
import com.it4895q.model.LoginRequest;
import com.it4895q.model.LoginResponse;
import com.it4895q.model.UserResponse;
import com.it4895q.security.JwtTokenUtil;
import com.it4895q.service.CustomUserDetailsService;
import com.it4895q.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest userRequest) {
		String username = userRequest.getUsername();
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		User user = userService.findByUserName(username);
		if (userDetails == null) {
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		String token = user.getToken();
		if (jwtTokenUtil.validateToken(token, userDetails) || token == null) {
			token = jwtTokenUtil.generateToken(userDetails);
		}
		return ResponseEntity.ok(token);
	}

	@PostMapping("/authenticateToken")
	public ResponseEntity<?> authenticateToken(@RequestBody JwtRequest request) {
		String token = request.getToken();

		final AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUserName(username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		log.info("test: {}", jwtTokenUtil.isTokenExpired(token));
		log.info("test: {}", jwtTokenUtil.validateToken(token, userDetails));
		log.info("test: {}", token);
		if (!jwtTokenUtil.validateToken(token, userDetails)) {
			authenticationResponse.setMessage("Token_expired");
			return ResponseEntity.ok(authenticationResponse);
		}

		authenticationResponse.setMessage("Token_invalid");

		UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getFirstName(),
				user.getLastName(), user.getLinkAvatar(), token);
		log.info("test: {}", userResponse.getToken());
		authenticationResponse.setUserResponse(userResponse);

		return ResponseEntity.ok(authenticationResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest userRequest) throws Exception {
		String username = userRequest.getUsername();
		String password = userRequest.getPassword();

		User user = userService.findByUserName(username);
		LoginResponse response = new LoginResponse();

		if (user == null || !bcryptEncoder.matches(password, user.getPassword())) {
			response.setMessage("User_or_password_wrong");
			return ResponseEntity.ok(response);
		}

		authenticate(username, password);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		final String token = jwtTokenUtil.generateToken(userDetails);
		userService.updateToken(username, token);

		response.setMessage("User_invalid");
		response.setToken(token);

		return ResponseEntity.ok(response);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
