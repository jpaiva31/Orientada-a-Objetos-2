import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Escorredor {
    private LinkedList<Prato> filaPratos;
    private static int tamanhoMaximo = 10;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());


    public int tamanhoFila() {
        return filaPratos.size();
    }

    public boolean filaVazia() {
        return filaPratos.isEmpty();
    }

    public boolean filaCheia() {
        return filaPratos.size() == tamanhoMaximo;
    }

    public synchronized void adicionarPrato(Prato prato) {

        while (this.filaCheia()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (filaPratos.size() >= tamanhoMaximo) {
            throw new IllegalStateException("A fila de pratos está cheia. Não é possível adicionar mais pratos.");
        }

        filaPratos.addLast(prato);

        if (filaPratos.size() == tamanhoMaximo) {
            logger.info("Total de pratos: " + filaPratos.size());
        }

        notify();
    }

    public synchronized Prato removerPrato() {

        while(this.filaVazia()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (this.filaVazia()) {
            throw new IllegalStateException("A fila de pratos está vazia. Não é possível remover pratos.");
        }

        Prato prato = filaPratos.pollFirst();

        if (filaPratos.size() == 0) {
            logger.info("Total de pratos: " + filaPratos.size());
        }

        notify();
        return prato;
    }

    public Escorredor () {
        Logger.getLogger("").setLevel(Level.INFO);
        this.filaPratos = new LinkedList<>();
    }
}
