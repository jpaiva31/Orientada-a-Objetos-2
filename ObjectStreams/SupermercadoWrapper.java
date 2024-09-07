import java.io.*;

public class SupermercadoWrapper {

    public static void serializarSupermercado(Supermercado supermercado, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(supermercado);
            System.out.println("Supermercado serializado com sucesso em " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Supermercado desserializarSupermercado(String fileName) {
        Supermercado supermercado = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            supermercado = (Supermercado) ois.readObject();
            System.out.println("Supermercado desserializado com sucesso de " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return supermercado;
    }

}
