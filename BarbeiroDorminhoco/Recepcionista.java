import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Recepcionista implements Runnable {
    private final SalaEspera salaEspera;
    private final Lock lock;
    private final Condition clientePronto;
    private final Condition barbeiroDormindo;
    private volatile boolean aberto;

    public Recepcionista(SalaEspera salaEspera, Lock lock, Condition clientePronto, Condition barbeiroDormindo) {
        this.salaEspera = salaEspera;
        this.lock = lock;
        this.clientePronto = clientePronto;
        this.barbeiroDormindo = barbeiroDormindo;
        this.aberto = true;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (aberto) {
            try {
                Thread.sleep((rand.nextInt(10) + 1));
                //Thread.sleep((rand.nextInt(10) + 1) * 1000);
                if (aberto) {
                    new Thread(new Cliente(salaEspera, lock, clientePronto, barbeiroDormindo)).start();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void fecharBarbearia() {
        aberto = false;
    }
}
