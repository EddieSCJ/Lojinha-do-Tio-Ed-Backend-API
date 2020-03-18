package com.codereddie.lojinha.domain.enums;

public enum ClientType {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer code;
	private String description;
	
	private ClientType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (ClientType clientType : ClientType.values()) {
			if(code.equals(clientType.getCode())) {
				return clientType;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+code);
	}
	
	
}
