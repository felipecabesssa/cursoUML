package com.br.felipesa.lojaUML.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.felipesa.lojaUML.domain.Categoria;
import com.br.felipesa.lojaUML.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
//  Injeção de dependencia, aqui estamos "navegando" entre as camadas do padrão REST
//  - essa é a camada CONTROLADORES REST que usa a CAMADA DE SERVIÇO para buscar (find) dados na DAO 'CAMADA DE ACESSO A DADOS (REPOSITORY)
//  Hierarquia: (ver as injeções de dependencia em cada classe que segue uma escadinha mesmo).
//  RESTCONTROLLER (CategoriaResource) - SERVICE (CategoriaService) - REPOSITORY (dados - CategoriaRepository) essa por sua vez é uma interface que extende la da JPAREPOSITORY.
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
//         O resposeEntity encapsula/armazena varias informações de requisição http em um serviço mestre
//         A ? siginifica que ele pode ser de qualquer tipo (caso não encontre o especifico)
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
//                            ok diz que foi tudo certo na requisição dai passa o obj como corpo da resposta.		

	}
	
}
