package br.com.alura.loja.testes;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
        
		//cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(3l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorCategoria("CELULARES");
		todos.forEach(produto -> System.out.println(produto.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Iphone 12");
		System.out.println("Preço do Iphone 12: " + precoDoProduto);
    }

//	private static void cadastrarProduto() {
//		Categoria celulares = new Categoria("CELULARES");
//        Produto celular = new Produto("Xiaomi Poco", "Celular Custo-Beneficio", new BigDecimal("1500"), celulares );
//
//        EntityManager em = JPAUtil.getEntityManager();
//        ProdutoDao produtoDao = new ProdutoDao(em);
//        CategoriaDao categoriaDao = new CategoriaDao(em);
// 
//        em.getTransaction().begin();
//
//        categoriaDao.cadastrar(celulares);
//        produtoDao.cadastrar(celular);
//
//        em.getTransaction().commit();
//        em.close();
//	}

}


