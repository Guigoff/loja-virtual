import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}

	}

}
