package com.it4895q.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

	private String text;

	private String postImage1;
	
	private String postImage2;
	
	private String postImage3;
	
	private String postImage4;

	private String postVideo;
	
	private String token;
	
	private Date createAt;
}
