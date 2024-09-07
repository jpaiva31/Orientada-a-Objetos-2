import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente implements Runnable {
    private final Lock lock;
    private final Condition clientePronto;
    private final Condition barbeiroDormindo;
    private final SalaEspera salaEspera;
    private volatile boolean cabeloCortado = false;
    private static final Logger logger = Logger.getLogger(Barbeiro.class.getName());

    public Cliente(SalaEspera salaEspera, Lock lock, Condition clientePronto, Condition barbeiroDormindo) {
        Logger.getLogger("").setLevel(Level.INFO);
        this.salaEspera = salaEspera;
        this.lock = lock;
        this.clientePronto = clientePronto;
        this.barbeiroDormindo = barbeiroDormindo;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            if (salaEspera.adicionarCliente(this)) {
                logger.info("Cliente entrou na barbearia.");
                barbeiroDormindo.signal();
                clientePronto.await();
                if (cabeloCortado) {
                    logger.info("Cliente saiu com o cabelo cortado.");
                }
            } else {
                logger.info("Cliente não conseguiu cadeira e foi embora.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void cortarCabelo() {
        cabeloCortado = true;
        clientePronto.signal();
    }
}
