package com.it4895q.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private Long id;

	private String postImage1;

	private String postImage2;

	private String postImage3;

	private String postImage4;

	private String postText;

	private String postVideo;

	private int amountLike;

	private Date createAt;

	private List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();

}
