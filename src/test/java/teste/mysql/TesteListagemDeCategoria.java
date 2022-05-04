package teste.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.guigoff.db.ConnectionFactory;
import br.com.guigoff.db.dao.CategoriaDAO;
import br.com.guigoff.db.modelo.Categoria;
import br.com.guigoff.db.modelo.Produto;

public class TesteListagemDeCategoria {

	public static void main(String[] args) throws SQLException {
		try (Connection connection = new ConnectionFactory().recuperaConexao()) {

			CategoriaDAO CategoriaDao = new CategoriaDAO(connection);
			List<Categoria> listaDeCategoria = CategoriaDao.listaComProdutos();
			listaDeCategoria.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				for (Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getDescricao());
				}

			});
		}
	}

}
