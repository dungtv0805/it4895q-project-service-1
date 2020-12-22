package com.it4895q.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String postImage1;

	private String postImage2;

	private String postImage3;

	private String postImage4;

	private String postText;

	private String postVideo;

	private int amountLike;

	private Date createAt;

	/**
	 * Relationship ManyToOne between Posts and User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Post))
			return false;
		return id != null && id.equals(((Post) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	/**
	 * Relationship OneToMany between Post and PostComments
	 */
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostComment> comments = new ArrayList<>();

	public void addComment(PostComment comment) {
		comment.setCreateAt(new Date());
		comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(PostComment comment) {
		comments.remove(comment);
		comment.setPost(null);
	}
}
