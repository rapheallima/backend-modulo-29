package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import produtoDao.IProdutoDAO;
import produtoDao.ProdutoDAO;

public class ProdutoTest {

	@Test
	public void cadastrarProdTest() throws Exception {

		IProdutoDAO dao = new ProdutoDAO();

		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("PC");

		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);

		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());

		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}

	@Test
	public void atualizarProdTest() throws Exception {

		IProdutoDAO dao = new ProdutoDAO();

		Produto produto = new Produto();
		produto.setCodigo("02");
		produto.setNome("Notebook");
		dao.cadastrar(produto);

		produto.setNome("Notebook Atualizado");
		Integer qtdAtualizada = dao.atualizar(produto);
		assertTrue(qtdAtualizada == 1);

		Produto produtoAtualizado = dao.consultar(produto.getCodigo());
		assertNotNull(produtoAtualizado);
		assertEquals("Notebook Atualizado", produtoAtualizado.getNome());

		dao.excluir(produto);
	}

	@Test
	public void buscarTodosProdTest() throws Exception {

		IProdutoDAO dao = new ProdutoDAO();

		Produto produto1 = new Produto();
		produto1.setCodigo("03");
		produto1.setNome("Mouse");
		dao.cadastrar(produto1);

		Produto produto2 = new Produto();
		produto2.setCodigo("04");
		produto2.setNome("Teclado");
		dao.cadastrar(produto2);

		List<Produto> produtos = dao.buscarTodos();
		assertNotNull(produtos);
		assertTrue(produtos.size() >= 2);

		assertTrue(produtos.stream().anyMatch(p -> p.getCodigo().equals("03") && p.getNome().equals("Mouse")));
		assertTrue(produtos.stream().anyMatch(p -> p.getCodigo().equals("04") && p.getNome().equals("Teclado")));

		dao.excluir(produto1);
		dao.excluir(produto2);
	}

}
