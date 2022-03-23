package com.br.felipesa.lojaUML.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.br.felipesa.lojaUML.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
//                    Aqui define a herança "Inheritance" sendo pagamento a mãe, tem duas formas de se fazer herança no java
//                    Ou criamos um "tabelão - JOINED" com todos atributos dos filhos, ou separamos cada filho em uma tabela especifica
//                    SINLE_TABLE. qundo cada filho tem poucos atributos melhor usar o joined mesmo, se tiver muitos com o joined gera mto valor null

//  A classe abstrata garante que eu não consiga instanciar objetos do tipo pagamento.
//  Pra instanciar ela eu tenho que dar um new em algum filho (fazemos isso no caso da herança óbviamente)
	
	@Id
	private Integer id;
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
//  A anotação @MapsId define que Pagamento e Pedido terão o mesmo id.
//	IMPORTANTE - Lembre que nesse caso de mesmo id não vamos colocar o generated value no id (para não gerar automaticamente)
//  Esse Id sera gerado automaticamente la na classe pedido (que tera o generated value) e ai o Pagamento herda esse id pelo @MapsId
	
	public Pagamento() {
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
