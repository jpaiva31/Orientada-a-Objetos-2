import java.util.logging.Logger;

public class Lavador implements Runnable {
    private Escorredor escorredor;
    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());
    private boolean emExecucao;

    public void changeEmExecucao() {
        emExecucao = !emExecucao;
    }

    public Lavador(Escorredor escorredor) {
        System.out.println("Lavador: Criado");
        this.escorredor = escorredor;
        emExecucao = true;
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
        while (emExecucao) {
            try {
                Prato pratoASerLavado = null;

                synchronized (escorredor) {
                    if (escorredor.filaCheia()) {
                        while (escorredor.filaCheia()) {
                            escorredor.wait();
                        }
                    }
                    pratoASerLavado = PratosSujosFactory.criarPratoSujo();
                    int tempoLavagem = calcularTempoLavagem(pratoASerLavado.getNivelSujeira());
                    logger.info("Lavador: Limpando prato " + pratoASerLavado.getNivelSujeira() + " por " + tempoLavagem + "ms...");

                    Thread.sleep(tempoLavagem);

                    escorredor.adicionarPrato(pratoASerLavado);
                    logger.info("Lavador: Prato limpo colocado no escorredor.");

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
