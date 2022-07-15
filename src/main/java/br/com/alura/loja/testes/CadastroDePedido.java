package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.alura.loja.vo.RelatorioDeVendasVO;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		 popularBancoDeDados();
		 
		 EntityManager em = JPAUtil.getEntityManager();
	     ProdutoDAO produtoDao = new ProdutoDAO(em);
	     ClienteDAO cDAO = new ClienteDAO(em);
	     
	     Produto produto1 = produtoDao.buscarPorId(1l);
	     Produto produto2 = produtoDao.buscarPorId(2l);
	     Produto produto3 = produtoDao.buscarPorId(3l);
		 Cliente cliente = cDAO.buscarPorId(1l);
		 Cliente cliente2 = cDAO.buscarPorId(2l);
	     
	     em.getTransaction().begin();
	     
		 Pedido pedido1 = new Pedido(cliente);
		 pedido1.adicionarItem(new ItemPedido(20, pedido1, produto1));
		 pedido1.adicionarItem(new ItemPedido(10, pedido1, produto3));
		 
		 Pedido pedido2 = new Pedido(cliente);
		 pedido2.adicionarItem(new ItemPedido(5, pedido2, produto2));
		 
		 Pedido pedido3 = new Pedido(cliente2);
		 pedido3.adicionarItem(new ItemPedido(10, pedido3, produto2));
		 pedido3.adicionarItem(new ItemPedido(40, pedido3, produto1));
		 pedido3.adicionarItem(new ItemPedido(15, pedido3, produto3));
		 
		 PedidoDAO pedidoDao = new PedidoDAO(em);
		 pedidoDao.cadastrar(pedido1);
		 pedidoDao.cadastrar(pedido2);
		 pedidoDao.cadastrar(pedido3);
		 
		 em.getTransaction().commit();
		 
		 BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		 System.out.println("Valor Total vendido: " + totalVendido);
		 
		 List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioDeVendas();
		 for (RelatorioDeVendasVO relatorioDeVendasVO : relatorio) {
			 
			 System.out.println(relatorioDeVendasVO);
			
		}
		 
		 
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
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);
 
        em.getTransaction().begin();

        categoriaDao.cadastrar(monitor);
        categoriaDao.cadastrar(periferico);
        categoriaDao.cadastrar(celular);
        
        produtoDao.cadastrar(p3);
        produtoDao.cadastrar(p2);
        produtoDao.cadastrar(p1);
        
        clienteDao.cadastrar(cliente2);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
	}

}
