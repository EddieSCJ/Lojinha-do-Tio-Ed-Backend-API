package com.codereddie.lojinha.domain.enums;

public enum PayamentState {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer code;
	private String description;
	
	private PayamentState(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PayamentState toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (PayamentState clientType : PayamentState.values()) {
			if(code.equals(clientType.getCode())) {
				return clientType;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+code);
	}
	
	
}
