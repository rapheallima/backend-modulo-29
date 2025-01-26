package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import clienteDao.ClienteDAO;
import clienteDao.IClienteDAO;

public class ClienteTest {

	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Raphael");

		Integer qtd = dao.cadastrar(cliente);
		assertTrue(qtd == 1);

		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());

		Integer qtdDel = dao.excluir(clienteBD);
		assertNotNull(qtdDel);
	}

	@Test
	public void atualizarProdTest() throws Exception {

		IClienteDAO dao = new ClienteDAO();

		Cliente cliente = new Cliente();
		cliente.setCodigo("02");
		cliente.setNome("Ana");
		dao.cadastrar(cliente);

		cliente.setNome("Ana Luiza");
		Integer qtdAtualizada = dao.atualizar(cliente);
		assertTrue(qtdAtualizada == 1);

		Cliente clienteAtualizado = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteAtualizado);
		assertEquals("Ana Luiza", clienteAtualizado.getNome());

		dao.excluir(cliente);
	}

	@Test
	public void buscarTodosClientesTest() throws Exception {

		IClienteDAO dao = new ClienteDAO();

		Cliente cliente1 = new Cliente();
		cliente1.setCodigo("03");
		cliente1.setNome("Carlos");
		dao.cadastrar(cliente1);

		Cliente cliente2 = new Cliente();
		cliente2.setCodigo("04");
		cliente2.setNome("Maria");
		dao.cadastrar(cliente2);

		List<Cliente> cliente = dao.buscarTodos();
		assertNotNull(cliente);
		assertTrue(cliente.size() >= 2);

		assertTrue(cliente.stream().anyMatch(p -> p.getCodigo().equals("03") && p.getNome().equals("Carlos")));
		assertTrue(cliente.stream().anyMatch(p -> p.getCodigo().equals("04") && p.getNome().equals("Maria")));

		dao.excluir(cliente1);
		dao.excluir(cliente2);
	}

}
