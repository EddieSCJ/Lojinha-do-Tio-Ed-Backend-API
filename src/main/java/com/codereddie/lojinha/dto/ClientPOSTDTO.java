package com.codereddie.lojinha.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClientPOSTDTO {

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 50, message = "O nome deve conter entre 5 e 50 caracteres")
	private String name;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF(message = "CPF inválido")
	private String cpfOuCnpj;

	@NotNull(message = "Preenchimento obrigatório")
	private Integer clientType;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 8, max = 14, message = "Telefone inválido em território brasileiro")
	private String phone;

	@Length(min = 8, max = 14, message = "Telefone inválido em território brasileiro")
	private String phoneux;

	@NotNull(message = "Preenchimento obrigatório")
	private Integer cityID;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String place;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String number;

	private String complement;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String neighborhood;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String CEP;

	public ClientPOSTDTO() {

	}

	public ClientPOSTDTO(String name, String email, String cpfOuCnpj, Integer clientType, String phone, String phoneux,
			Integer cityID, String place, String number, String complement, String neighborhood, String CEP) {
		super();
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.clientType = clientType;
		this.phone = phone;
		this.phoneux = phoneux;
		this.cityID = cityID;
		this.place = place;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.CEP = CEP;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneux() {
		return phoneux;
	}

	public void setPhoneux(String phoneux) {
		this.phoneux = phoneux;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String	CEP) {
		this.CEP = CEP;
	}

}
