import java.io.Serializable;
import java.util.Date;


public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private Date validade;
    private int quantidade;
    private double preco;

    public Produto(String codigo, String nome, Date validade, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public Date getValidade() {
        return this.validade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getPreco() {
        return this.preco;
    }


}
