package com.br.felipesa.lojaUML;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.felipesa.lojaUML.domain.Categoria;
import com.br.felipesa.lojaUML.domain.Cidade;
import com.br.felipesa.lojaUML.domain.Cliente;
import com.br.felipesa.lojaUML.domain.Endereco;
import com.br.felipesa.lojaUML.domain.Estado;
import com.br.felipesa.lojaUML.domain.ItemPedido;
import com.br.felipesa.lojaUML.domain.Pagamento;
import com.br.felipesa.lojaUML.domain.PagamentoComBoleto;
import com.br.felipesa.lojaUML.domain.PagamentoComCartao;
import com.br.felipesa.lojaUML.domain.Pedido;
import com.br.felipesa.lojaUML.domain.Produto;
import com.br.felipesa.lojaUML.domain.enums.EstadoPagamento;
import com.br.felipesa.lojaUML.domain.enums.TipoCliente;
import com.br.felipesa.lojaUML.repositories.CategoriaRepository;
import com.br.felipesa.lojaUML.repositories.CidadeRepository;
import com.br.felipesa.lojaUML.repositories.ClienteRepository;
import com.br.felipesa.lojaUML.repositories.EnderecoRepository;
import com.br.felipesa.lojaUML.repositories.EstadoRepository;
import com.br.felipesa.lojaUML.repositories.ItemPedidoRepository;
import com.br.felipesa.lojaUML.repositories.PagamentoRepository;
import com.br.felipesa.lojaUML.repositories.PedidoRepository;
import com.br.felipesa.lojaUML.repositories.ProdutoRepository;

@SpringBootApplication
public class LojaUmlApplication implements CommandLineRunner {
//	Esse CommandLineRunner permite instanciar objetos aqui pelo método run.
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepositoy;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojaUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//      Instanciando categorias.
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
//      Salvando as categorias instanciadas no banco.		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
//      Instanciando produtos.
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
		
//      salvando os produtos instanciados no banco.
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
//      Instanciando estados.
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
//      Instanciando as cidades.
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
//      Mostrando pros estados suas cidades.
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
//      Salvando Estado e Cidade no banco de dados.
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

//      Cliente Endereco e Telefone
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		clienteRepositoy.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
//      Instanciando Pedido com seu pagamento e filhos pagamentoComBoleto e pagamentoComCartao
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
//      Instanciando itens de pedido
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
		
	}

}
