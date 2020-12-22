package com.it4895q.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeLineResponse {
	UserResponse userResponse;
	
	List<PostResponse> postResponses = new ArrayList<PostResponse>();
}
