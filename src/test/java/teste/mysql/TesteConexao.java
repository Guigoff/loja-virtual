package teste.mysql;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.guigoff.db.ConnectionFactory;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		Connection con =  ConnectionFactory.recuperaConexao();
		
	}

}
