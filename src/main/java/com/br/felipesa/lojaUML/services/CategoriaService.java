package com.br.felipesa.lojaUML.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.felipesa.lojaUML.domain.Categoria;
import com.br.felipesa.lojaUML.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	// Quando usamos o autowired na injeção de dependencia o spring automaticamente instancia essa classe.

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
