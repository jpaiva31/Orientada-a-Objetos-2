import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.time.LocalDate;

public class Funcionario implements Serializable {
    private static final AtomicInteger codigoGerador = new AtomicInteger(1); // Gerador de código automático
    private int codigo;
    private String nome;
    private int idade;
    private LocalDate nascimento;
    private double salario;

    public Funcionario(String nome, int idade, LocalDate nascimento, double salario) {
        this.codigo = codigoGerador.getAndIncrement(); // Código gerado automaticamente
        this.nome = nome;
        this.idade = idade;
        this.nascimento = nascimento;
        this.salario = salario;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", nascimento=" + nascimento +
                ", salario=" + salario +
                '}';
    }
}
