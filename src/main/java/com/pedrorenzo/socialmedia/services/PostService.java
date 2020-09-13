package com.pedrorenzo.socialmedia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrorenzo.socialmedia.entities.Post;
import com.pedrorenzo.socialmedia.repositories.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	@Autowired
	public PostService(final PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Post save(final Post post) {
		return postRepository.save(post);
	}

}
