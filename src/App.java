import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private Lavador lavador;
    private Enxugador enxugador;
    private Thread lavadorThread;
    private Thread enxugadorThread;

    public App() {
        Logger.getLogger("").setLevel(Level.INFO);
        Escorredor escorredor = new Escorredor();
        enxugador = new Enxugador(escorredor);
        lavador = new Lavador(escorredor);
        lavadorThread = new Thread(lavador);
        enxugadorThread = new Thread(enxugador);

        lavadorThread.setUncaughtExceptionHandler((thread, throwable) -> {
            System.exit(1);
        });

        // Define um UncaughtExceptionHandler para o enxugadorThread
        enxugadorThread.setUncaughtExceptionHandler((thread, throwable) -> {
            System.exit(1);
        });
    }

    public void work() {
        enxugadorThread.start();
        lavadorThread.start();
    }

    public void stop() {
        lavador.changeEmExecucao();
        enxugador.changeEmExecucao();
    }

    public static void main(String[] args) {
        App app = new App();
        app.work();

        try {
            // Aguarda 2 minutos
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.stop();
    }
}
