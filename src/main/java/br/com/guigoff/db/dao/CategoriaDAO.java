package br.com.guigoff.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.guigoff.db.modelo.Categoria;
import br.com.guigoff.db.modelo.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> lista() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CATEGORIA")) {
			pstm.execute();
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}

	public List<Categoria> listaComProdutos() throws SQLException {
		Categoria ultima = null;
		List<Categoria> categorias = new ArrayList<>();
		try (PreparedStatement pstm = connection.prepareStatement("SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN"
				+ " PRODUTO P ON C.ID = P.CATEGORIA_ID")) {
			pstm.execute();
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					if(ultima == null || !ultima.getNome().equals(rs.getString(2))) {
						Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					}
					Produto produto = new Produto (rs.getInt(3), rs.getString(4), rs.getString(5));
					ultima.adicionar(produto);
				}
			}
		}
		return categorias;
	}
}
