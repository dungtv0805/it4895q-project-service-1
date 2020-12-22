package com.it4895q.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommentResponse {
	private Long id;

	private String review;

	private Date createAt;

	private UserResponse userResponse;

	public CommentResponse(Long id, String review, Date createAt, UserResponse userResponse) {
		this.id = id;
		this.review = review;
		this.createAt = createAt;
		this.userResponse = userResponse;
	}

	public CommentResponse() {

	}
}
