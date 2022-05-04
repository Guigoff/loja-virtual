package teste.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.guigoff.db.ConnectionFactory;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		Connection con =  ConnectionFactory.recuperaConexao();
		
		PreparedStatement stm = con.prepareStatement("select id, nome, descricao from produto");
		stm.execute();
		
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			
			
			System.out.println(id + " - " + nome + " - " + descricao);
		
	


		}
	}
}
