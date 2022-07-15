package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class PerformaceConsultas {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		
		em.close();
		System.out.println(pedido.getCliente().getNome());
		
		
	}
	
	private static void popularBancoDeDados() {
		Categoria monitor = new Categoria("Monitor");
		Categoria celular = new Categoria("Celular");
		Categoria periferico = new Categoria("Periférico");
		
        Produto p1 = new Produto("AOC Hero", "27 polegadas c/G-Sync", new BigDecimal("2000"), monitor );
        Produto p2 = new Produto("Iphone 13", "Lançamento Apple", new BigDecimal("5000"), celular);
        Produto p3 = new Produto("Mouse Razer", "Mouse gamer c/RGB", new BigDecimal("150"), periferico);
        
        Cliente cliente = new Cliente("Claudio", "587741");
        Cliente cliente2 = new Cliente("Jessica", "885741");
        
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(5, pedido, p1));
        pedido.adicionarItem(new ItemPedido(10, pedido, p3));
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);
        PedidoDAO pedidoDao = new PedidoDAO(em);
 
        em.getTransaction().begin();

        categoriaDao.cadastrar(monitor);
        categoriaDao.cadastrar(periferico);
        categoriaDao.cadastrar(celular);
        
        produtoDao.cadastrar(p1);
        produtoDao.cadastrar(p2);
        produtoDao.cadastrar(p3);
        
        clienteDao.cadastrar(cliente2);
        clienteDao.cadastrar(cliente);
        
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();
	}

}
