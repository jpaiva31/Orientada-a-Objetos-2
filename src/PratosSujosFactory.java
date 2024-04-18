import java.util.Random;

public class PratosSujosFactory {
    
    public Prato.NivelSujeira setNivelSujeira() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 30) {
            return Prato.NivelSujeira.BAIXO;
        } else if (chance < 90) {
            return Prato.NivelSujeira.MEDIO;
        } else {
            return Prato.NivelSujeira.ENGORDURADO;
        }
    }

    public static Prato criarPratoSujo() {
        PratosSujosFactory factory = new PratosSujosFactory();

        return new Prato(factory.setNivelSujeira());
    }

}