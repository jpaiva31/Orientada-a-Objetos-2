import java.util.LinkedList;
import java.util.logging.Logger;


public class Escorredor {
    private LinkedList<Prato> filaPratos;
    private static int tamanhoMaximo = 0;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());

    public void adicionarPrato(Prato prato) {
        if (filaPratos.size() >= tamanhoMaximo) {
            throw new IllegalStateException("A fila de pratos está cheia. Não é possível adicionar mais pratos.");
        }
        filaPratos.addLast(prato);

        if (filaPratos.size() == tamanhoMaximo) {
            logger.info("Total de pratos: " + filaPratos.size());
        }
    }

    public Prato removerPrato() {
        if (filaPratos.isEmpty()) {
            throw new IllegalStateException("A fila de pratos está vazia. Não é possível remover pratos.");
        }
        return filaPratos.pollFirst();
    }

    public int tamanhoFila() {
        return filaPratos.size();
    }

    public boolean filaVazia() {
        return filaPratos.isEmpty();
    }

    public boolean filaCheia() {
        return filaPratos.size() == tamanhoMaximo;
    }

    public Escorredor (int tamanhoMaximoFila) {
        Escorredor.tamanhoMaximo = tamanhoMaximoFila;

        this.filaPratos = new LinkedList<>();
    }
}
