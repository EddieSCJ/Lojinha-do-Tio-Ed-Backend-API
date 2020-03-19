package com.codereddie.lojinha.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Embeddable
public class OrderItemṔK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orderr orderr;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Orderr getOrderr() {
		return orderr;
	}
	public void setOrderr(Orderr orderr) {
		this.orderr = orderr;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderr == null) ? 0 : orderr.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemṔK other = (OrderItemṔK) obj;
		if (orderr == null) {
			if (other.orderr != null)
				return false;
		} else if (!orderr.equals(other.orderr))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
}
