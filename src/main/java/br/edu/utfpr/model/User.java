package br.edu.utfpr.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.edu.utfpr.util.HashGenerator;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private Long id;
	@Column(name = "usr_name", nullable = false)
	private String name;
	@Column(name = "usr_username", nullable = false, unique = true, updatable = false)
	private String username;
	@Column(name = "usr_password", nullable = false)
	private String password;
	@Transient
	private Map<String, String> errors;
	@Transient
	private UserRole userRole;
	
	public User(Long id, String name, String username, String password, UserRole userRole) {
		this.setId(id);
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setUserRole(userRole);
	}

	public User() {
		this.setId(null);
		this.setName("");
		this.setUsername("");
		this.password = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long userId) {
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean isValid() {
		this.validate();
		return this.errors.isEmpty();
	}
	
	private void validate() {
		this.errors = new HashMap<String, String>();

		if (this.name.length() < 3 || this.name == null) {
			this.errors.put("name", "Nome invalido.");
		}

		if (this.username.length() < 3 || this.username == null) {
			this.errors.put("username", "Usuario invalido.");
		}

		if (this.password == null || this.password.length() < 3) {
			this.errors.put("password", "Senha invalida.");
		}else {
			this.password = HashGenerator.getSha256(password);
		}

	}
}
