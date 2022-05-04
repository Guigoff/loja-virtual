package br.com.guigoff.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource ds;
	
	public ConnectionFactory() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("root");
		
		cpds.setMaxPoolSize(5);
		
		this.ds = cpds;
	}
	public Connection recuperaConexao() throws SQLException {
		
		return this.ds.getConnection();
		
	}

}
