package br.edu.utfpr.models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User implements Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(nullable=false) 
	private String name;
	
	@Column(nullable=false) 
	private String username;
	
	@Column(nullable=false) 
	private String password;
/*	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
	    @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
	    @JoinColumn(name = "EMPRESA", referencedColumnName = "EMPRESA")
	})
	private Role role;
	*/
	@Transient
	private Map<String, String> errors;
	
	public User(long userId, String name, String username, String password) {
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public User() {
	}
	
	public long getuserId() {
		return userId;
	}

	public void setId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	@Override
	public boolean isValid() {
		this.isValid();
		return this.errors.isEmpty();
	}

	private void validate() {
		this.errors = new HashMap<String, String>();

		if(this.name.length() < 3 || this.name == null) {
			this.errors.put("name", "Nome invalido");
		}
		
		if(this.username.length() < 3 || this.username == null) {
			this.errors.put("username", "Usuario invalido");
		}
		
		if(this.password.length() < 3 || this.password == null) {
			this.errors.put("password", "Senha invalida");
		}
	}
}
