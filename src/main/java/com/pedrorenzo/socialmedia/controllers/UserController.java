package com.pedrorenzo.socialmedia.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrorenzo.socialmedia.entities.User;
import com.pedrorenzo.socialmedia.exceptions.UserNotFoundException;
import com.pedrorenzo.socialmedia.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/{id}")
	public EntityModel<User> retrieveUser(@PathVariable(value = "id") final Integer id) {
		final Optional<User> user = userService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User id not found: " + id);
		}

		final EntityModel<User> resource = EntityModel.of(user.get());
		final WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping
	public ResponseEntity<Object> createUser(@Valid @RequestBody final User user) {
		final User savedUser = userService.save(user);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable(value = "id") final Integer id) {
		userService.deleteById(id);
	}

}
