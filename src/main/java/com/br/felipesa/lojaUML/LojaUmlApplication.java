package com.br.felipesa.lojaUML;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.felipesa.lojaUML.domain.Categoria;
import com.br.felipesa.lojaUML.repositories.CategoriaRepository;

@SpringBootApplication
public class LojaUmlApplication implements CommandLineRunner {
//	Esse CommandLineRunner permite instanciar objetos aqui pelo método run.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojaUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
