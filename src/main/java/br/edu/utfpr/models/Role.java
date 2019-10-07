package br.edu.utfpr.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

	private String role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
			@JoinColumn(name = "user_username", referencedColumnName = "username") })
	private User user;

	public Role(String role, User user) {
		this.role = role;
		this.user = user;
	}
}
