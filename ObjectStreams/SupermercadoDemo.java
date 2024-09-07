import java.util.Date;

public class SupermercadoDemo {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java SupermercadoDemo <write|read>");
            return;
        }

        String operacao = args[0];

        if ("write".equalsIgnoreCase(operacao)) {
            Endereco endereco = new Endereco("Rua A", "123", "Apto 1", "São Paulo");
            Supermercado supermercado = new Supermercado("001", "Supermercado XYZ", endereco);

            Produto produto1 = new Produto("P001", "Arroz", new Date(), 10, 5.99);
            Produto produto2 = new Produto("P002", "Feijão", new Date(), 20, 4.99);
            Produto produto3 = new Produto("P003", "Macarrão", new Date(), 30, 3.99);

            supermercado.adicionarProduto(produto1);
            supermercado.adicionarProduto(produto2);
            supermercado.adicionarProduto(produto3);

            SupermercadoWrapper.serializarSupermercado(supermercado, "dados.txt");

        } else if ("read".equalsIgnoreCase(operacao)) {
            Supermercado supermercadoDesserializado = SupermercadoWrapper.desserializarSupermercado("dados.txt");

            if (supermercadoDesserializado != null) {
                System.out.println("Código: " + supermercadoDesserializado.getCodigo());
                System.out.println("Nome: " + supermercadoDesserializado.getNome());
                System.out.println("Endereço: " + supermercadoDesserializado.getEndereco().getLogradouro() + ", " +
                                   supermercadoDesserializado.getEndereco().getNumero() + ", " +
                                   supermercadoDesserializado.getEndereco().getComplemento() + ", " +
                                   supermercadoDesserializado.getEndereco().getCidade());
                System.out.println("Produtos: ");
                supermercadoDesserializado.getProdutos().forEach(produto -> {
                    System.out.println("Código: " + produto.getCodigo() +
                                       ", Nome: " + produto.getNome() +
                                       ", Validade: " + produto.getValidade() +
                                       ", Quantidade: " + produto.getQuantidade() +
                                       ", Preço: R$" + produto.getPreco());
                });
            }

        } else {
            System.out.println("Operação desconhecida. Uso: java SupermercadoDemo <write|read>");
        }
    }
}
