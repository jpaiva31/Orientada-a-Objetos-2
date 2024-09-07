import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Barbeiro implements Runnable {
    private Cliente clienteAtual;
    private final Lock lock;
    private final Condition clientePronto;
    private final Condition barbeiroDormindo;
    private final SalaEspera salaEspera;
    private volatile boolean aberto;
    private static final Logger logger = Logger.getLogger(Barbeiro.class.getName());

    public Barbeiro(SalaEspera salaEspera, Lock lock, Condition clientePronto, Condition barbeiroDormindo) {
        Logger.getLogger("").setLevel(Level.INFO);
        this.salaEspera = salaEspera;
        this.lock = lock;
        this.clientePronto = clientePronto;
        this.barbeiroDormindo = barbeiroDormindo;
        this.aberto = true;
    }

    @Override
    public void run() {
        while (aberto || !salaEspera.isEmpty()) {
            lock.lock();
            try {
                while (salaEspera.isEmpty()) {
                    if (!aberto) {
                        return;
                    }
                    logger.info("Barbeiro dormindo.");
                    barbeiroDormindo.await();
                }
                clienteAtual = salaEspera.removeCliente();
                cortarCabelo();
                clientePronto.signal();
                clienteAtual = null;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    private void cortarCabelo() {
        Random rand = new Random();
        int tempoCorte = rand.nextInt(10) + 1;
        try {
            logger.info("Cortando cabelo. Duração: " + tempoCorte + " segundos.");
            Thread.sleep(tempoCorte);
            //Thread.sleep(tempoCorte * 1000);
            logger.info("Cabelinho cortado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void fecharBarbearia() {
        lock.lock();
        try {
            aberto = false;
            barbeiroDormindo.signal();
        } finally {
            lock.unlock();
        }
    }
}
