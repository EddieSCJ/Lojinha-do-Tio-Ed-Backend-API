package com.codereddie.lojinha.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.codereddie.lojinha.domain.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOuCnpj;
	private Integer clientType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	List<Orderr> orders = new ArrayList<Orderr>();
	
	@ElementCollection
	@CollectionTable(
		name = "phone"
			)
	private Set<String> phones = new HashSet<String>();

	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	private List<Address> addresses = new ArrayList<Address>();
	
	
	public Client() {
		
	}
	
	public Client(Integer id, String name, String email, String cpfOuCnpj, ClientType clientType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		if(clientType == null) {
			this.clientType = null;
		}
		else {
			this.clientType = clientType.getCode();
		}
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getClientType() {
		return ClientType.toEnum(this.clientType).getDescription();
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCode();
	}
	 
	public Set<String> getPhones() {
		return phones;
	}

	public void addPhone(String phone) {
		this.phones.add(phone);
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public List<Orderr> getOrders() {
		return orders;
	}

	public void addOrder(Orderr order) {
		this.orders.add(order);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
