package com.projetoOrange.enderecousuario.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoOrange.enderecousuario.entities.Endereco;
import com.projetoOrange.enderecousuario.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> list = service.findAll();
		return ResponseEntity.ok().body(list);
		// list.add(new Endereco(1L, "Rua do Catete", "340", "Sala 301", "Catete", "Rio de Janeiro","RJ","20210045"));
		// list.add(new Endereco(2L, "Rua Barata Ribeiro", "850", "Apt. 304", "Copacabana", "Rio de Janeiro","RJ","22210045"));
	}	
}