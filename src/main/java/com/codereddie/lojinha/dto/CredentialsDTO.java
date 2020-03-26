package com.codereddie.lojinha.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public CredentialsDTO() {
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setEmail(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
