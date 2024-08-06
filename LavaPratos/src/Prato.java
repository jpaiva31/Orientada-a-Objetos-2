
public class Prato {
    enum NivelSujeira {
        BAIXO,
        MEDIO,
        ENGORDURADO
    }

    private int numeroSerie;
    private static int proximoNumeroDeSerie = 1;
    private NivelSujeira nivelSujeira;

    public NivelSujeira getNivelSujeira() {
        return this.nivelSujeira;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Prato(NivelSujeira nivelSujeira) {
        this.nivelSujeira = nivelSujeira;
        this.numeroSerie = proximoNumeroDeSerie;
        proximoNumeroDeSerie++;
    }
}
