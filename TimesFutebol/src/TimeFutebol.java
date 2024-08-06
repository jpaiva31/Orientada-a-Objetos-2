import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TimeFutebol {
    private final String nome;
    private final Calendar fundacao;
    private final String tecnico;
    private final double arrecadacao;
    private final List<Jogador> jogadores;
    private final List<Torcedor> torcedores;

    public TimeFutebol(String nome, Calendar fundacao, String tecnico, double arrecadacao, List<Jogador> jogadores, List<Torcedor> torcedores) {
        this.nome = nome;
        this.fundacao = fundacao;
        this.tecnico = tecnico;
        this.arrecadacao = arrecadacao;
        this.jogadores = Collections.unmodifiableList(new ArrayList<>(jogadores));
        this.torcedores = new ArrayList<>(torcedores);
    }

    public String getNome() {
        return nome;
    }

    public Calendar getFundacao() {
        return fundacao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public double getArrecadacao() {
        return arrecadacao;
    }

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }

    public List<Torcedor> getTorcedores() {
        return torcedores;
    }

    public void adicionarTorcedor(Torcedor torcedor) {
        this.torcedores.add(torcedor);
    }

    public void removerTorcedor(Torcedor torcedor) {
        this.torcedores.remove(torcedor);
    }

}
