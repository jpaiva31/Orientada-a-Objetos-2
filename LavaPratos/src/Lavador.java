import java.util.logging.Level;
import java.util.logging.Logger;

public class Lavador implements Runnable {
    private static Escorredor escorredor;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());
    private boolean emExecucao;

    public void changeEmExecucao() {
        emExecucao = !emExecucao;
    }

    public Lavador(Escorredor escorredor) {
        Logger.getLogger("").setLevel(Level.INFO);
        Lavador.escorredor = escorredor;
        emExecucao = true;
    }


    private int calcularTempoLavagem(Prato.NivelSujeira nivel) {
        switch (nivel) {
            case BAIXO:
                return 3;
            case MEDIO:
                return 5;
            case ENGORDURADO:
                return 10;
            default:
                throw new IllegalArgumentException("Nível de sujeira inválido: " + nivel);
        }
    }

    @Override
    public void run() {
        while (emExecucao) {
            try {
                Prato pratoASerLavado = null;

                pratoASerLavado = PratosSujosFactory.criarPratoSujo();
                int tempoLavagem = calcularTempoLavagem(pratoASerLavado.getNivelSujeira());
                logger.info("Lavador: Lavando prato " + pratoASerLavado.getNivelSujeira() + " por " + tempoLavagem + "ms...");

                Thread.sleep(tempoLavagem);

                escorredor.adicionarPrato(pratoASerLavado);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        }
    }
}
