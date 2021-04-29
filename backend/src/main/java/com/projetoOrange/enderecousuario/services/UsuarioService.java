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

import com.projetoOrange.enderecousuario.dto.UsuarioDTO;
import com.projetoOrange.enderecousuario.entities.Usuario;
import com.projetoOrange.enderecousuario.repositories.UsuarioRepository;
import com.projetoOrange.enderecousuario.services.exceptions.DatabaseException;
import com.projetoOrange.enderecousuario.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		
		List<UsuarioDTO> listDto = list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		
		return listDto;
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional <Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Código não encontrado !"));
		
		return new UsuarioDTO(entity, entity.getEnderecos());
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		//entity.setLogradouro(dto.getLogradouro());
		//entity.setNumero(dto.getNumero());
		//entity.setComplemento(dto.getComplemento());
		//entity.setBairro(dto.getBairro());
		//entity.setCidade(dto.getCidade());
		//entity.setEstado(dto.getEstado());
		//entity.setCep(dto.getCep());
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getOne(id);
			//entity.setLogradouro(dto.getLogradouro());
			//entity.setNumero(dto.getNumero());
			//entity.setComplemento(dto.getComplemento());
			//entity.setBairro(dto.getBairro());
			//entity.setCidade(dto.getCidade());
			//entity.setEstado(dto.getEstado());
			//entity.setCep(dto.getCep());
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		}	
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID de usuário não encontrato "+id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID de usuario não encontrato "+id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade do BD !");
		}
	}
}
