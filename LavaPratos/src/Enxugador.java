import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enxugador implements Runnable {
    private Escorredor escorredor;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());
    private boolean emExecucao;

    public void changeEmExecucao() {
        emExecucao = !emExecucao;
    }

    public Enxugador(Escorredor escorredor) {
        Logger.getLogger("").setLevel(Level.INFO);
        this.escorredor = escorredor;
        emExecucao = true;
    }

    @Override
    public void run() {
        while (emExecucao == true ||  !escorredor.filaVazia()) {
            try {
                Prato pratoASerEnxugado = null;

                pratoASerEnxugado = escorredor.removerPrato();

                Random rand = new Random();
                int tempoEnxugamento = rand.nextInt(8) + 3;

                logger.info("Enxugador: Enxugando prato " + pratoASerEnxugado.getNivelSujeira() + " por " + tempoEnxugamento + "ms...");

                Thread.sleep(tempoEnxugamento);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (IllegalStateException e) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        }
    }
}
