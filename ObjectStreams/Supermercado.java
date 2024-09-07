import java.io.Serializable;
import java.util.LinkedList;


public class Supermercado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private Endereco endereco;
    private LinkedList<Produto> listaProdutos;

    public Supermercado(String codigo, String nome, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.listaProdutos = new LinkedList<>();
    }

    public void adicionarProduto(Produto produto) {
        if (this.listaProdutos.size() < 3) {
            this.listaProdutos.add(produto);
        } else {
            System.out.println("O supermercado já possui 3 produtos cadastrados.");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public LinkedList<Produto> getProdutos() {
        return this.listaProdutos;
    }

    public String getCodigo() {
        return this.codigo;
    }

}
