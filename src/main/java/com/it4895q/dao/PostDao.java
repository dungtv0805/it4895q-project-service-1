package com.it4895q.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.it4895q.entity.Post;
import com.it4895q.entity.PostComment;

@Repository
public class PostDao {

	@Autowired
	EntityManager entityManager;

	public void savePost(Post post) {
		entityManager.persist(post);
	}

	public void updatePost(Post post) {
		entityManager.merge(post);
	}
	
	public void saveComment(PostComment comment) {
		Post post = entityManager.getReference(Post.class, 1L);
		
//		entityManager.persist();
	}
}
