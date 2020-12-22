package com.it4895q.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it4895q.dao.PostDao;
import com.it4895q.dao.UserDao;
import com.it4895q.dao.UserRepository;
import com.it4895q.entity.Post;
import com.it4895q.entity.User;
import com.it4895q.model.PostRequest;
import com.it4895q.security.JwtTokenUtil;

@Service
public class PostService {

	@Autowired
	PostDao postDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	public void savePost(PostRequest post) {
		Post newPost = new Post();
		newPost.setPostImage1(post.getPostImage1());
		newPost.setPostImage2(post.getPostImage2());
		newPost.setPostImage3(post.getPostImage3());
		newPost.setPostImage4(post.getPostImage4());
//		newPost.setPostText(post.getPostText());
		newPost.setPostVideo(post.getPostVideo());

		String username = jwtTokenUtil.getUsernameFromToken(post.getToken());
		User user = userRepository.findByUsername(username);
		user.addPost(newPost);

		userDao.updateUser(user);
	}

	public void deletePost(PostRequest post) {

	}
}
