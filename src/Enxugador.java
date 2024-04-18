import java.util.Random;
import java.util.logging.Logger;

public class Enxugador implements Runnable {
    private Escorredor escorredor;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());

    public Enxugador(Escorredor escorredor) {
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Prato pratoASerEnxugado = null;

                synchronized (escorredor) {
                    if (escorredor.filaVazia()) {
                        while (escorredor.filaVazia()) {
                            escorredor.wait();
                        }
                    }

                    pratoASerEnxugado = escorredor.removerPrato();

                    Random rand = new Random();
                    int tempoEnxugamento = rand.nextInt(8) + 3;

                    logger.info("Enxugador: Enxugando prato " + pratoASerEnxugado.getNivelSujeira() + " por " + tempoEnxugamento + "ms...");

                    Thread.sleep(tempoEnxugamento);
                    escorredor.notifyAll();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
