package com.codereddie.lojinha.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Orderr implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instantDate;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private  Payament payament;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "orderItemPK.orderr")
	private Set<OrderItem> itens = new HashSet<OrderItem>();
	
	public Orderr() {
		
	}
	
	public Orderr(Integer id, Date instantDate, Client client, Address address) {
		super();
		this.id = id;
		this.instantDate = instantDate;
		this.client = client;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstantDate() {
		return instantDate;
	}

	public void setInstantDate(Date instantDate) {
		this.instantDate = instantDate;
	}

	public Payament getPayament() {
		return payament;
	}

	public void setPayament(Payament payament) {
		this.payament = payament;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public Set<OrderItem> getItens() {
		return itens;
	}

	public Double getTotal() {
		Double total = 0.0;
		for(OrderItem orderItem : itens) {
			total+=orderItem.getSubtotal();
		}
		return total;
	}
	
	public void addItem(OrderItem item) {
		this.itens.add(item);
	}
	
	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orderr other = (Orderr) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
