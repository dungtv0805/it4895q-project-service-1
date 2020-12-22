package com.it4895q.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it4895q.entity.User;
import com.it4895q.model.LoginRequest;
import com.it4895q.service.UserService;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
	@Autowired
	private UserService userService;

	@RequestMapping({ "/hello" })
	public String firstPage(@RequestBody LoginRequest request) {

		User user = userService.findByUserName(request.getUsername());
		return user.getUsername();
	}

}