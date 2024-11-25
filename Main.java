import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            produtoDAO.createTable();

            // Inserir produtos
            produtoDAO.inserirProduto(new Produto(0, "Informática", "Notebook Dell", 2.5, 10, "kg"));
            produtoDAO.inserirProduto(new Produto(0, "Limpeza", "Detergente", 1.0, 50, "litro"));
            produtoDAO.inserirProduto(new Produto(0, "Casa & Decoração", "Sofá", 20.0, 5, "kg"));
            produtoDAO.inserirProduto(new Produto(0, "Informática", "Mouse", 0.2, 30, "kg"));
            produtoDAO.inserirProduto(new Produto(0, "Limpeza", "Esponja", 0.05, 100, "kg"));

            // Alterar produtos
            produtoDAO.alterarProduto(1, "Notebook Dell Inspiron");
            produtoDAO.alterarProduto(2, "Detergente Neutro");

            // Excluir produto
            produtoDAO.excluirProduto(5);

            // Listar produtos
            for (Produto produto : produtoDAO.listarProdutos()) {
                System.out.println(produto.getDescricao());
            }

            // Exibir detalhes de produtos
            Produto produtoDetalhado = produtoDAO.detalharProduto(1);
            if (produtoDetalhado != null) {
                System.out.println("Detalhes do produto: " + produtoDetalhado.getDescricao());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
