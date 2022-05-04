package teste.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.guigoff.db.ConnectionFactory;

public class TesteInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.recuperaConexao()) {

			connection.setAutoCommit(false);

			try (PreparedStatement stm = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
				
				
				adicionarVariavel("Corsair K61", "Teclado Corsair K61", stm);
				adicionarVariavel("Logitech G430", "Mouse Logitech G430", stm);

				connection.commit();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK");
				connection.rollback();
			}

		}

	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		stm.execute();

		try (ResultSet rst = stm.getGeneratedKeys()) {
			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("Item inserido com o id: " + id);
			}

		}
	}

}
