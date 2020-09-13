package com.pedrorenzo.socialmedia.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User details")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should have at least 2 characters")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = "Birth date should be in the past")
	private Date birthDate;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User(final Integer id, final String name, final Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	protected User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(final List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
