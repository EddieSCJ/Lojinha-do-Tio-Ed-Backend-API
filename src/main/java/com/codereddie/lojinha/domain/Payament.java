package com.codereddie.lojinha.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.codereddie.lojinha.domain.enums.PayamentState;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payament implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer payamentState;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	private Orderr order;
	
	public Payament() {
		
	}

	public Payament(Integer id, PayamentState payamentState, Orderr order) {
		super();
		this.id = id;
		this.payamentState = (payamentState == null) ? null : payamentState.getCode();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PayamentState getPayamentState() {
		return PayamentState.toEnum(this.payamentState);
	}

	public void setPayamentState(PayamentState payamentState) {
		this.payamentState = payamentState.getCode();
	}

	public Orderr getOrder() {
		return order;
	}

	public void setOrder(Orderr order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Payament other = (Payament) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
