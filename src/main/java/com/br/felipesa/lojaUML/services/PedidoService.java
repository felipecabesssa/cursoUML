package com.br.felipesa.lojaUML.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.felipesa.lojaUML.domain.Pedido;
import com.br.felipesa.lojaUML.exceptions.ObjetoNaoEncontradoException;
import com.br.felipesa.lojaUML.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	// Quando usamos o autowired na injeção de dependencia o spring automaticamente instancia essa classe.

	public Pedido buscaPorIdService(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}

	
	
}
