package com.codereddie.lojinha.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.codereddie.lojinha.domain.enums.PayamentState;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PayamentWithBankBill extends Payament{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymanetDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date expirationDate;
	
	public PayamentWithBankBill() {
		
	}

	public PayamentWithBankBill(Integer id, PayamentState payamentState, Orderr order, Date paymanetDate,
			Date expirationDate) {
		super(id, payamentState, order);
		this.paymanetDate = paymanetDate;
		this.expirationDate = expirationDate;
	}

	public Date getPaymanetDate() {
		return paymanetDate;
	}

	public void setPaymanetDate(Date paymanetDate) {
		this.paymanetDate = paymanetDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
	
	
}
