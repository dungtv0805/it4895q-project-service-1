package com.it4895q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it4895q.model.PostRequest;
import com.it4895q.security.JwtTokenUtil;
import com.it4895q.service.PostService;
import com.it4895q.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class NewFeedController {

	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@PostMapping("/createNewPost")
	public ResponseEntity<?> createNewPost(@RequestBody PostRequest postRequest) {

//		String token = postRequest.getToken();
//		String username = jwtTokenUtil.getUsernameFromToken(token);

		postService.savePost(postRequest);

		return ResponseEntity.ok(postRequest);

	}
}
