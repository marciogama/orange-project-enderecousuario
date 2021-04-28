package com.projetoOrange.enderecousuario.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoOrange.enderecousuario.dto.EnderecoDTO;
import com.projetoOrange.enderecousuario.entities.Endereco;
import com.projetoOrange.enderecousuario.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Transactional(readOnly = true)
	public List<EnderecoDTO> findAll() {
		List<Endereco> list = repository.findAll();
		
		List<EnderecoDTO> listDto = list.stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
		
		return listDto;
	}

}
