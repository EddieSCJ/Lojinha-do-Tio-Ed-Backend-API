package com.codereddie.lojinha.domain;

import javax.persistence.Entity;

import com.codereddie.lojinha.domain.enums.PayamentState;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("payamentWithCreditCard")
@Entity
public class PayamentWithCreditCard extends Payament {

	private static final long serialVersionUID = 1L;
	
	private Integer installmentsNumber;
	
	public PayamentWithCreditCard() {
		
	}

	public PayamentWithCreditCard(Integer id, PayamentState payamentState, Orderr order, Integer installmentsNumber) {
		super(id, payamentState, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
	
	
	
}
