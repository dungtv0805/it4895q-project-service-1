package com.it4895q.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.it4895q.entity.User;

@Repository
@Transactional
public class UserDao {
	@Autowired
	EntityManager entityManager;
	
	public void updateUser(User user) {
		entityManager.merge(user);
	}
}
