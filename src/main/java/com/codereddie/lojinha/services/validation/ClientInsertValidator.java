package com.codereddie.lojinha.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.enums.ClientType;
import com.codereddie.lojinha.dto.ClientPOSTDTO;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.resources.exceptions.FieldMessage;
import com.codereddie.lojinha.services.validation.br.ValidateBRData;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientPOSTDTO> {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientPOSTDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getClientType() == null || objDto.getCpfOuCnpj() == null) {
			list.add(new FieldMessage("cpfOuCnpj", "Campos nulos não são aceitos"));
		
		}
		if (objDto.getClientType() == ClientType.PESSOAJURIDICA.getCode()
				&& !ValidateBRData.conferirCNPJ(objDto.getCpfOuCnpj().trim())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ INVÁLIDO"));
			
		} else if (objDto.getClientType() == ClientType.PESSOAFISICA.getCode()
				&& !ValidateBRData.conferirCPF(objDto.getCpfOuCnpj().trim())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF INVÁLIDO"));
		
		} else if (ClientType.PESSOAFISICA.getCode() != objDto.getClientType()
				&& ClientType.PESSOAJURIDICA.getCode() != objDto.getClientType()) {
			list.add(new FieldMessage("ClientType",
					"Tipo do cliente não reconhecido, favor selecionar 1 para pessoa física e 2 para pessoa jurídica"));
		}

		Client client = clientRepository.findByEmail(objDto.getEmail());
		if(client != null) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}
		client = null;
		client = clientRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if(client != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ já cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
