package com.it4895q.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	private String firstName;

	private String lastName;

	private String linkAvatar;

//	@Column(nullable = false)
	private String token;
	
	/**
	 * Relationship OneToMany between User and Posts
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts = new ArrayList<Post>();

	public void addPost(Post post) {
		post.setCreateAt(new Date());
		posts.add(post);
		post.setUser(this);
	}
	
	public void removePost(Post post) {
		posts.remove(post);
		post.setUser(null);
	}
	
}
