package com.pedrorenzo.socialmedia.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Post details")
@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	private String description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Post(final Integer id, final String description, final User user) {
		this.id = id;
		this.description = description;
		this.user = user;
	}

	protected Post() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
