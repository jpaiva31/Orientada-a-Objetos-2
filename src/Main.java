public class Main {
    private Lavador lavador;
    private Enxugador enxugador;
    private Thread lavadorThread;
    private Thread enxugadorThread;

    public Main() {
        Escorredor escorredor = new Escorredor(10);
        lavador = new Lavador(escorredor);
        enxugador = new Enxugador(escorredor);
        lavadorThread = new Thread(lavador);
        enxugadorThread = new Thread(enxugador);
    }

    public void work() {
        lavadorThread.start();
        enxugadorThread.start();
    }

    public void stop() {
        lavador.changeEmExecucao();
    }

    public static void main(String[] args) {
        Main app = new Main();
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
