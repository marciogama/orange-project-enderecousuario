package com.projetoOrange.enderecousuario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoOrange.enderecousuario.entities.Endereco;
import com.projetoOrange.enderecousuario.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Transactional(readOnly = true)
	public List<Endereco> findAll() {
		return repository.findAll();
	}

}
