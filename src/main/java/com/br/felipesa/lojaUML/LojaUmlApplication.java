package com.br.felipesa.lojaUML;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.felipesa.lojaUML.domain.Categoria;
import com.br.felipesa.lojaUML.domain.Produto;
import com.br.felipesa.lojaUML.repositories.CategoriaRepository;
import com.br.felipesa.lojaUML.repositories.ProdutoRepository;

@SpringBootApplication
public class LojaUmlApplication implements CommandLineRunner {
//	Esse CommandLineRunner permite instanciar objetos aqui pelo método run.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojaUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//      Instanciando categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
//      Salvando as categorias instanciadas no banco		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
//      Instanciando produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
//      mostrando pras categorias respectivas quais produtos estão associados a elas e inserindo.		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
//      mostrando pros produtos a quais categorias eles estão associados e incluindo.
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
//      salvando os produtos instanciados no banco
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
