package br.com.guigoff.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.guigoff.db.modelo.Categoria;
import br.com.guigoff.db.modelo.Produto;

public class ProdutoDAO {
	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	public void salvar(Produto produto) throws SQLException {
		try (PreparedStatement pstm = connection.prepareStatement(
				"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			
			pstm.execute();
			
			try(ResultSet rs = pstm.getGeneratedKeys()){
				while(rs.next()) {
					produto.setId(rs.getInt(1));
				}
				
			}
		}
	}
	
	public List<Produto> Listar() throws SQLException{
		List<Produto> produtos = new ArrayList<Produto>();
		try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUTO")) {
			pstm.execute();
			
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3));
					produtos.add(produto);
				}
				
			}
		}
		
		return produtos;
	}


	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?")) {
			pstm.setInt(1, ct.getId());
			pstm.execute();
			
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3));
					produtos.add(produto);
				}
				
			}
		}
		
		return produtos;
	}
}
