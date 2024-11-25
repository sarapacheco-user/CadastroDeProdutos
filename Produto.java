public class Produto {
    private int id;
    private String tipo;
    private String descricao;
    private double peso;
    private int quantidade;
    private String unidadeMedida;

    public Produto(int id, String tipo, String descricao, double peso, int quantidade, String unidadeMedida) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.peso = peso;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public double getPeso() { return peso; }
    public int getQuantidade() { return quantidade; }
    public String getUnidadeMedida() { return unidadeMedida; }
}

