package com.br.felipesa.lojaUML.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.felipesa.lojaUML.exceptions.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ExcecaoRecursoHandler {
	
//  Anotação pra dizer que esse método é um tratador de exceção da exceção em parenteses
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest request) {
		
		ErroPadrao err =  new ErroPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
//      O HttpStatus.STATUS.value() - o value manda na forma de número inteiro.
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
