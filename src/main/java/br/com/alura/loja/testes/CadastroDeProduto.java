package br.com.alura.loja.testes;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
        
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorCategoria("Periférico");
		todos.forEach(produto -> System.out.println(produto.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Mouse Razer");
		System.out.println("Preço do mouse: " + precoDoProduto);
    }

	private static void cadastrarProduto() {
		Categoria pc = new Categoria("Periférico");
        Produto celular = new Produto("Mouse Razer", "Mouse c/fio RGB", new BigDecimal("250"), pc );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
 
        em.getTransaction().begin();

        categoriaDao.cadastrar(pc);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
	}

}


