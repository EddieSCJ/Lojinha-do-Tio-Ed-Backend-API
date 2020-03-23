package com.codereddie.lojinha.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Length(min=3, max = 50, message = "O nome deve conter entre 5 e 50 caracteres")
	private String name;

	@Email(message = "Email inválido")
	private String email;

	public ClientDTO() {
		
	}

	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.email = client.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
