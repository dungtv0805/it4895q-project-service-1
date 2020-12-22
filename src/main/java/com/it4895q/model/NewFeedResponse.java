package com.it4895q.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFeedResponse {

	private UserResponse userResponse;

	private List<PostResponse> posts = new ArrayList<PostResponse>();
}
