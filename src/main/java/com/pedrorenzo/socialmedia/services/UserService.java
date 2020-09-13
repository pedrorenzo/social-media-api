package com.pedrorenzo.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrorenzo.socialmedia.entities.User;
import com.pedrorenzo.socialmedia.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(final Integer id) {
		return userRepository.findById(id);
	}

	public User save(final User user) {
		return userRepository.save(user);
	}

	public void deleteById(final Integer id) {
		userRepository.deleteById(id);
	}

}
