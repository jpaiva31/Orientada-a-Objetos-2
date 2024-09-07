import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppBin {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Digite o nome do funcionário " + (i + 1) + ": ");
            String nome = scanner.nextLine();

            System.out.println("Digite a idade do funcionário " + (i + 1) + ": ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite a data de nascimento (YYYY-MM-DD) do funcionário " + (i + 1) + ": ");
            String nascimentoStr = scanner.nextLine();
            LocalDate nascimento = LocalDate.parse(nascimentoStr);

            System.out.println("Digite o salário do funcionário " + (i + 1) + ": ");
            double salario = scanner.nextDouble();
            scanner.nextLine();

            Funcionario funcionario = new Funcionario(nome, idade, nascimento, salario);
            funcionarios.add(funcionario);

            System.out.println("Digite o logradouro do funcionário " + (i + 1) + ": ");
            String logradouro = scanner.nextLine();

            System.out.println("Digite o número do endereço: ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o CEP do endereço: ");
            String cep = scanner.nextLine();

            Endereco endereco = new Endereco(logradouro, numero, cep);
            enderecos.add(endereco);
        }

        scanner.close();

        escreverArquivoBinario("func.bin", funcionarios);
        escreverArquivoBinario("endereco.bin", enderecos);

        lerArquivoBinario("func.bin", "endereco.bin");
    }

    private static void escreverArquivoBinario(String nomeArquivo, List<?> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void lerArquivoBinario(String arquivoFunc, String arquivoEndereco) {
        try (
            ObjectInputStream oisFunc = new ObjectInputStream(new FileInputStream(arquivoFunc));
            ObjectInputStream oisEndereco = new ObjectInputStream(new FileInputStream(arquivoEndereco))
        ) {
            List<Funcionario> funcionarios = (List<Funcionario>) oisFunc.readObject();
            List<Endereco> enderecos = (List<Endereco>) oisEndereco.readObject();

            for (int i = 0; i < funcionarios.size(); i++) {
                System.out.println("Funcionário: " + funcionarios.get(i));
                System.out.println("Endereço: " + enderecos.get(i));
                System.out.println("------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
