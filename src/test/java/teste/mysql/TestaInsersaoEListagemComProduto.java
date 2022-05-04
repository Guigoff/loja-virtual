package teste.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.guigoff.db.ConnectionFactory;
import br.com.guigoff.db.dao.ProdutoDAO;
import br.com.guigoff.db.modelo.Produto;

public class TestaInsersaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto monitor = new Produto("Monitor 21", "Monitor 21 Polegadas Samsung");
		
		try (Connection connection = new ConnectionFactory().recuperaConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(monitor);
			List<Produto> listaDeProdutos = produtoDao.Listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}

	}

}
