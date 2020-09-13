package com.pedrorenzo.socialmedia.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrorenzo.socialmedia.entities.Post;
import com.pedrorenzo.socialmedia.entities.User;
import com.pedrorenzo.socialmedia.exceptions.UserNotFoundException;
import com.pedrorenzo.socialmedia.services.PostService;
import com.pedrorenzo.socialmedia.services.UserService;

@RestController
@RequestMapping("users/{id}/posts")
public class PostController {

	private final UserService userService;
	private final PostService postService;

	@Autowired
	public PostController(final UserService userService, final PostService postService) {
		this.userService = userService;
		this.postService = postService;
	}

	@GetMapping
	public List<Post> retrieveUser(@PathVariable(value = "id") final Integer id) {
		final Optional<User> user = userService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User id not found: " + id);
		}

		return user.get().getPosts();
	}

	@PostMapping
	public ResponseEntity<Object> createUser(@PathVariable(value = "id") final Integer id,
			@RequestBody final Post post) {
		final Optional<User> user = userService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User id not found: " + id);
		}

		post.setUser(user.get());
		final Post savedPost = postService.save(post);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
