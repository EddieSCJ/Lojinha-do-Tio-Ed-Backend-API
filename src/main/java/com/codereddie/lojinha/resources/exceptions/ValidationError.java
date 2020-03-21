package com.codereddie.lojinha.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> messagesList = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return messagesList;
	}

	public void addMessagesList(String fieldName, String fieldMessage) {
		this.messagesList.add(new FieldMessage(fieldName, fieldMessage));
	}
	
	

	
	

}
