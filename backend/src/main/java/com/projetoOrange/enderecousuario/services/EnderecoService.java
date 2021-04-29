package com.projetoOrange.enderecousuario.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoOrange.enderecousuario.dto.EnderecoDTO;
import com.projetoOrange.enderecousuario.entities.Endereco;
import com.projetoOrange.enderecousuario.repositories.EnderecoRepository;
import com.projetoOrange.enderecousuario.services.exceptions.DatabaseException;
import com.projetoOrange.enderecousuario.services.exceptions.ResourceNotFoundException;

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
		
	@Transactional(readOnly = true)
	public EnderecoDTO findById(Long id) {
		Optional <Endereco> obj = repository.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Código não encontrado !"));
		
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = new Endereco();
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setEstado(dto.getEstado());
		entity.setCep(dto.getCep());
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO update(Long id, EnderecoDTO dto) {
		try {
			Endereco entity = repository.getOne(id);
			entity.setLogradouro(dto.getLogradouro());
			entity.setNumero(dto.getNumero());
			entity.setComplemento(dto.getComplemento());
			entity.setBairro(dto.getBairro());
			entity.setCidade(dto.getCidade());
			entity.setEstado(dto.getEstado());
			entity.setCep(dto.getCep());
			entity = repository.save(entity);
			return new EnderecoDTO(entity);
		}	
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID de endereço não encontrato "+id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID de endereço não encontrato "+id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade do BD !");
		}
	}
}
