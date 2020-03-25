package com.codereddie.lojinha.domain.enums;

public enum Profiles {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private Integer code;
	private String description;
	
	private Profiles(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Profiles toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (Profiles profile : Profiles.values()) {
			if(code.equals(profile.getCode())) {
				return profile;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+code);
	}
	
	
}
