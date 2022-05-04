package teste.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.guigoff.db.ConnectionFactory;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperaConexao();
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		stm.setInt(1, 4);
		stm.execute();
		
		Integer linhasDeletadas = stm.getUpdateCount();
		System.out.println("Linhas deletadas: " +linhasDeletadas);
		
		

	}

}
