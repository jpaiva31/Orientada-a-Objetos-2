import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SalaEspera {
    private final BlockingQueue<Cliente> fila;

    public SalaEspera() {
        this.fila = new LinkedBlockingQueue<>(5);
    }

    public boolean adicionarCliente(Cliente cliente) {
        return fila.offer(cliente);
    }

    public Cliente removeCliente() throws InterruptedException {
        return fila.take();
    }

    public boolean isEmpty() {
        return fila.isEmpty();
    }
}
