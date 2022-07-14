package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		 popularBancoDeDados();
		 
		 EntityManager em = JPAUtil.getEntityManager();
	     ProdutoDao produtoDao = new ProdutoDao(em);
	     ClienteDAO cDAO = new ClienteDAO(em);
	     
	     Produto produto = produtoDao.buscarPorId(1l);
		 Cliente cliente = cDAO.buscarPorId(1l);
	     
	     em.getTransaction().begin();
	     
		 Pedido pedido = new Pedido(cliente);
		 pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		 
		 PedidoDAO pedidoDao = new PedidoDAO(em);
		 pedidoDao.cadastrar(pedido);
		 
		 em.getTransaction().commit();
		
	}
	
	private static void popularBancoDeDados() {
		Categoria pc = new Categoria("Monitor");
        Produto celular = new Produto("AOC Hero", "27 polegadas c/G-Sync", new BigDecimal("2000"), pc );
        Cliente cliente = new Cliente("Claudio", "587741");
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDAO clienteDao = new ClienteDAO(em);
 
        em.getTransaction().begin();

        categoriaDao.cadastrar(pc);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
	}

}
