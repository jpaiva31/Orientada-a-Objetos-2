import java.util.Random;


public class Prato {
    enum NivelSujeira {
        BAIXO,
        MEDIO,
        ENGORDURADO
    }

    private int numeroSerie;
    private static int proximoNumeroDeSerie = 1;
    private NivelSujeira nivelSujeira; 

    public NivelSujeira setNivelSujeira() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 30) {
            return NivelSujeira.BAIXO;
        } else if (chance < 90) {
            return NivelSujeira.MEDIO;
        } else {
            return NivelSujeira.ENGORDURADO;
        }
    }

    public NivelSujeira getNivelSujeira(NivelSujeira nivelSujeira) {
        return this.nivelSujeira;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Prato() {
        this.nivelSujeira = setNivelSujeira();
        this.numeroSerie = proximoNumeroDeSerie;
        proximoNumeroDeSerie++;
    } 
}