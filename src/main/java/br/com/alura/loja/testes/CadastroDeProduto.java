package br.com.alura.loja.testes;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
        
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(3l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorCategoria("Smart TV");
		todos.forEach(produto -> System.out.println(produto.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Smart TV Samsung");
		System.out.println("Preço da TV Samsung: " + precoDoProduto);
    }

	private static void cadastrarProduto() {
		Categoria pc = new Categoria("Periférico");
        Produto celular = new Produto("Mouse Razer", "Mouse c/fio RGB", new BigDecimal("250"), pc );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
 
        em.getTransaction().begin();

        categoriaDao.cadastrar(pc);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
	}

}


