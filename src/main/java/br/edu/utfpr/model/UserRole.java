package br.edu.utfpr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class UserRole {
	@Id
	@Column(name="usr_username")
	private String username;
	
	@JoinColumn(name="rol_abrev")
	private String role;

	public UserRole(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public UserRole() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
