import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;


public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        logger.info("Iniciando o aplicativo...");

        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador("Jogador 1", 30));
        jogadores.add(new Jogador("Jogador 2", 25));

        List<Torcedor> torcedores = new ArrayList<>();
        torcedores.add(new Torcedor("Torcedor 1", 20));
        torcedores.add(new Torcedor("Torcedor 2", 22));

        Calendar fundacao = Calendar.getInstance();
        fundacao.set(1920, Calendar.JANUARY, 1);

        TimeFutebol time = new TimeFutebol("Corinthians", fundacao, "Técnico 1", 1000000, jogadores, torcedores);

        Runnable adicionarTorcedor = () -> {
            Torcedor torcedor = new Torcedor("Torcedor 3", 21);
            logger.info("Adicionando " + torcedor.getNome() + "...");
            time.adicionarTorcedor(torcedor);
            logger.info("Torcedor " + torcedor.getNome() + " adicionado.");
        };

        Runnable removerTorcedor = () -> {
            Torcedor torcedor = time.getTorcedores().get(0);
            logger.info("Removendo " + torcedor.getNome() + "...");
            time.removerTorcedor(torcedor);
            logger.info("Torcedor " + torcedor.getNome() + " removido.");
        };

        Thread thread1 = new Thread(adicionarTorcedor);
        Thread thread2 = new Thread(removerTorcedor);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Thread interrompida", e);
        }

        logger.info("Torcedores restantes: " + time.getTorcedores().size());
        logger.info("Aplicativo finalizado.");
    }
}
