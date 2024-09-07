import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Barbearia {
    private final Lock lock = new ReentrantLock();
    private final Condition clientePronto = lock.newCondition();
    private final Condition barbeiroDormindo = lock.newCondition();
    private final SalaEspera salaEspera = new SalaEspera();
    private final Barbeiro barbeiro = new Barbeiro(salaEspera, lock, clientePronto, barbeiroDormindo);
    private final Recepcionista recepcionista = new Recepcionista(salaEspera, lock, clientePronto, barbeiroDormindo);

    public void abrir() {
        new Thread(barbeiro).start();
        new Thread(recepcionista).start();
    }

    public void fechar() {
        recepcionista.fecharBarbearia();
        barbeiro.fecharBarbearia();
    }

    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia();
        barbearia.abrir();
        try {
            Thread.sleep(60000); // A barbearia abre por 1 minuto
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        barbearia.fechar();
    }
}
