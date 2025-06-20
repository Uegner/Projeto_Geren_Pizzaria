package pizzaria.service;

import pizzaria.model.Pedido;
import pizzaria.model.Pizza;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeArquivos {

    private static final String NOME_ARQUIVO = "pedidos.txt";

    public void salvarPedido(Pedido pedido) {

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = pedido.getData().format(formatador);


        String nomeCliente = pedido.getCliente().getNome();
        int numeroPedido = pedido.getNumero();


        StringBuilder conteudoParaSalvar = new StringBuilder();
        for (Pizza pizza : pedido.getPizzas()) {
            conteudoParaSalvar.append(numeroPedido).append("--")
                    .append(dataFormatada).append("--")
                    .append(nomeCliente).append("--")
                    .append(pizza.getNome()).append("--")
                    .append(String.format("%.2f", pizza.getPreco())).append("--")
                    .append(pizza.getTipo()).append("\n");
        }


        try {
            Path arquivo = Paths.get(NOME_ARQUIVO);

            Files.writeString(arquivo, conteudoParaSalvar.toString(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println("Pedido salvo com sucesso no arquivo " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o pedido no arquivo: " + e.getMessage());
        }
    }
}