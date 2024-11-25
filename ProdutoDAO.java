import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "AQUIESTAVAMINHASENHA!";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INT AUTO_INCREMENT PRIMARY KEY,
                tipo VARCHAR(50),
                descricao TEXT,
                peso DOUBLE,
                quantidade INT,
                unidade_medida ENUM('metro', 'metro quadrado', 'litro', 'kg')
            );
        """;
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (tipo, descricao, peso, quantidade, unidade_medida) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getTipo());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPeso());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.setString(5, produto.getUnidadeMedida());
            pstmt.executeUpdate();
        }
    }

    public void alterarProduto(int id, String novaDescricao) throws SQLException {
        String sql = "UPDATE produtos SET descricao = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novaDescricao);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Produto> listarProdutos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("peso"),
                    rs.getInt("quantidade"),
                    rs.getString("unidade_medida")
                ));
            }
            return produtos;
        }
    }

    public Produto detalharProduto(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("descricao"),
                        rs.getDouble("peso"),
                        rs.getInt("quantidade"),
                        rs.getString("unidade_medida")
                    );
                }
            }
        }
        return null;
    }
}

