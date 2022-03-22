package com.br.felipesa.lojaUML.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.felipesa.lojaUML.domain.Cliente;
import com.br.felipesa.lojaUML.exceptions.ObjetoNaoEncontradoException;
import com.br.felipesa.lojaUML.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	// Quando usamos o autowired na injeção de dependencia o spring automaticamente instancia essa classe.

	public Cliente buscaPorIdService(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}

	
	
}
