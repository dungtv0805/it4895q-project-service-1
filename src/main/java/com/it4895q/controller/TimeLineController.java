package com.it4895q.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it4895q.entity.Post;
import com.it4895q.entity.PostComment;
import com.it4895q.entity.User;
import com.it4895q.model.AuthenticationResponse;
import com.it4895q.model.CommentResponse;
import com.it4895q.model.PostResponse;
import com.it4895q.model.TimeLineResponse;
import com.it4895q.model.UserResponse;
import com.it4895q.service.UserService;

@RestController
@RequestMapping("/api")
public class TimeLineController {

	@Autowired
	private UserService userService;

	@PostMapping("/time-line")
	public ResponseEntity<?> getTimeLine(@RequestBody AuthenticationResponse authenticationResponse) {
		TimeLineResponse timeLineResponse = new TimeLineResponse();
		timeLineResponse.setUserResponse(authenticationResponse.getUserResponse());

		User user = userService.findByUserName(authenticationResponse.getUserResponse().getUsername());

		List<PostResponse> postResponses = new ArrayList<PostResponse>();
		for (Post p : user.getPosts()) {

			List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();
			for (PostComment comment : p.getComments()) {

				User userComment = comment.getUser();
				UserResponse userCommentResponse = new UserResponse(userComment.getId(), userComment.getUsername(),
						userComment.getFirstName(), userComment.getLastName(), userComment.getLinkAvatar(), null);

				CommentResponse commentResponse = new CommentResponse(comment.getId(), comment.getReview(),
						comment.getCreateAt(), userCommentResponse);
				commentResponses.add(commentResponse);
			}

			PostResponse postResponse = new PostResponse(p.getId(), p.getPostImage1(), p.getPostImage2(),
					p.getPostImage3(), p.getPostImage4(), p.getPostText(), p.getPostVideo(), p.getAmountLike(),
					p.getCreateAt(), commentResponses);
			postResponses.add(postResponse);
		}

		timeLineResponse.setPostResponses(postResponses);

		return ResponseEntity.ok(timeLineResponse);
	}
}
