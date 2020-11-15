package com.it4895q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.it4895q.dao.UserDao;
import com.it4895q.dao.UserRepository;
import com.it4895q.entity.User;
import com.it4895q.model.RegisterRequest;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public void save(RegisterRequest user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setPhone(user.getPhone());
		userRepository.save(newUser);
	}

	public void updateToken(String username, String token) {
		User updateableUser = userRepository.findByUsername(username);
		updateableUser.setToken(token);
		userDao.updateUser(updateableUser);
	}
	
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
}
