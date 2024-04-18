public class Lavador implements Runnable {
    private Escorredor escorredor;

    public Lavador(Escorredor escorredor) {
        this.escorredor = escorredor;
    }

    private int calcularTempoLavagem(Prato.NivelSujeira nivel) {
        switch (nivel) {
            case BAIXO:
                return 3;
            case MEDIO:
                return 5;
            case ENGORDURADO:
                return 10;
            default:
                throw new IllegalArgumentException("Nível de sujeira inválido: " + nivel);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Prato pratoASerLavado = null;

                synchronized (escorredor) {
                    pratoASerLavado = escorredor.removerPrato();
                    int tempoLavagem = calcularTempoLavagem(pratoASerLavado.getNivelSujeira());
                    System.out.println("Lavador: Limpando prato " + pratoASerLavado.getNivelSujeira() + " por " + tempoLavagem + "ms...");
                    
                    Thread.sleep(tempoLavagem);
                    escorredor.notifyAll();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
