package com.br.felipesa.lojaUML.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
//  Método para varrer todas as opções de enuns e comparar um a um com a quantidade de opções ate achar a que bate.
//  Com uma leve segurança contra valores vazios "null", se for null ele lança a exceção.

//  Método estático roda mesmo sem vc instanciar objeto.	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}
	
}

// Para usar o ENUM basta a palavra que o java atribui um número correspondente.
// Todo o resto do código é para evitar que algum desavisado inclua uma palavra antes das outras e quebre a aplicação.
// A descrição (1, descricao) é opcional.

