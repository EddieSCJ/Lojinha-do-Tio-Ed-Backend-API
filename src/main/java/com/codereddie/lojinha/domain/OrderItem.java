package com.codereddie.lojinha.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK orderItemPK = new OrderItemPK();
	
	private Double discount;
	private Double price;
	private Integer quantity;

	public OrderItem() {
		
	}

	public OrderItem(Orderr order, Product product, Double discount, Integer quantity, Double price) {
		super();
		this.orderItemPK.setOrderr(order);
		this.orderItemPK.setProduct(product);
		this.discount = discount;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderItemPK getorderItemPK() {
		return orderItemPK;
	}

	public void setorderItemPK(OrderItemPK orderItemPK) {
		this.orderItemPK = orderItemPK;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@JsonIgnore
	public Orderr getOrder() {
		return orderItemPK.getOrderr();
	}
	
	public Product getProduct() {
		return orderItemPK.getProduct();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderItemPK == null) ? 0 : orderItemPK.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (orderItemPK == null) {
			if (other.orderItemPK != null)
				return false;
		} else if (!orderItemPK.equals(other.orderItemPK))
			return false;
		return true;
	}
	
	

}
