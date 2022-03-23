package com.br.felipesa.lojaUML.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

// @EmbeddedId quer dizer que ele é um id embutido em um tipo auxiliar
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
//  como é um objeto auxiliar a gente tem que instanciar.	
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
	}

//  Dentro do construtor teria o ItemPedidoPK, só que qualquer outro programador não entenderia o que é isso,
//  dai passamos no lugar dele o pedido e o produto e atribuimos eles dentro do objeto id. com id.setPedido(pedido) - esse 
//  entre parenteses é o que foi passado e id.setProduto(produto)
//  não podemos esquecer de gerar o get pra eles na mão - Isso é para que a gente tenha acesso ao pedido e produto fora da
//  classe itemPedido.
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
}
