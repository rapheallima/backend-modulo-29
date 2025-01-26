package clienteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao_JDBC.ConnectionFactory;
import domain.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO CLIENTE (CODIGO, NOME) VALUES (?, ?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !stm.isClosed()) {
				connection.close();
			}
		}

	}

	@Override
	public Cliente consultar(String codigo) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM CLIENTE WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));

			}
			return cliente;
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !stm.isClosed()) {
				connection.close();
			}
		}

	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM CLIENTE WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !stm.isClosed()) {
				connection.close();
			}
		}

	}

	@Override
	public List<Cliente> buscarTodos() throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente> clientes = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM CLIENTE";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer atualizar(Cliente cliente) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE CLIENTE SET NOME = ? WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !connection.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
}
