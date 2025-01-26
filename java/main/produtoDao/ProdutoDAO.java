package produtoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao_JDBC.ConnectionFactory;
import domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO PRODUTO (CODIGO, NOME) VALUES (?, ?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
		}

	}

	@Override
	public Produto consultar(String codigo) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PRODUTO WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();

			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));

			}
			return produto;
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
	public Integer excluir(Produto produto) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM PRODUTO WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
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
	public Integer atualizar(Produto produto) throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PRODUTO SET NOME = ? WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getCodigo());
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

	@Override
	public List<Produto> buscarTodos() throws Exception {

		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PRODUTO";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				produtos.add(produto);
			}
			return produtos;
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

}
