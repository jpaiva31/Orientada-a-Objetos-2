import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppTxt {
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

        escreverArquivoTexto("func.txt", funcionarios);
        escreverArquivoTexto("endereco.txt", enderecos);

        lerArquivoTexto("func.txt", "endereco.txt");
    }

    private static void escreverArquivoTexto(String nomeArquivo, List<?> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Object obj : lista) {
                writer.write(obj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lerArquivoTexto(String arquivoFunc, String arquivoEndereco) {
        try (
            BufferedReader readerFunc = new BufferedReader(new FileReader(arquivoFunc));
            BufferedReader readerEndereco = new BufferedReader(new FileReader(arquivoEndereco))
        ) {
            String linhaFunc;
            String linhaEndereco;

            while ((linhaFunc = readerFunc.readLine()) != null && (linhaEndereco = readerEndereco.readLine()) != null) {
                System.out.println("Funcionário: " + linhaFunc);
                System.out.println("Endereço: " + linhaEndereco);
                System.out.println("------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
