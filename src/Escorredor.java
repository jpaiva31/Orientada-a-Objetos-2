import java.util.LinkedList;


public class Escorredor {
    private LinkedList<Prato> filaPratos;
    private static int tamanhoMaximo = 0;

    public void adicionarPrato(Prato prato) {
        if (filaPratos.size() >= tamanhoMaximo) {
            throw new IllegalStateException("A fila de pratos está cheia. Não é possível adicionar mais pratos.");
        }
        filaPratos.addLast(prato);
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

    public Escorredor (int tamanhoMaximoFila) {
        Escorredor.tamanhoMaximo = tamanhoMaximoFila;

        this.filaPratos = new LinkedList<>();
    }
}