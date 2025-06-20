package pizzaria.main;

import pizzaria.model.*;
import pizzaria.service.GerenciadorDeArquivos;
import pizzaria.service.PreparoPedido; // Supondo que esta classe exista
import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // --- PREPARAÇÃO DAS PIZZAS E CARDÁPIO ---
        Pizza p1 = new PizzaTradicional("Frango", 30, "");
        Pizza p2 = new PizzaEspecial("Marguerita", 35, "");
        Pizza p3 = new PizzaTradicional("Calabresa", 30, "");
        Pizza p4 = new PizzaEspecial("Da casa", 35,"");

        // Calcula os preços iniciais
        p1.calcularPreco();
        p2.calcularPreco(); // Aplica 15%
        p3.calcularPreco();
        p4.calcularPreco(); // Aplica 15%

        Cardapio.pizzasDisponiveis.add(p1);
        Cardapio.pizzasDisponiveis.add(p2);
        Cardapio.pizzasDisponiveis.add(p3);
        Cardapio.pizzasDisponiveis.add(p4);

        // --- PREPARAÇÃO DOS CLIENTES ---
        Cliente c1 = new Cliente("Wagner", "123", "Rua Inatel");
        Cliente c2 = new Cliente("Yan", "321", "Rua Vinte");
        Cliente c3 = new Cliente("Neymar", "100", "Vila belmiro");
        Cliente c4 = new Cliente("Gabigol", "200", "Cruzeiro");

        // --- CRIAÇÃO DOS PEDIDOS E ADIÇÃO A UMA LISTA ---
        List<Pedido> todosOsPedidos = new ArrayList<>();

        // Pedido 1
        Pedido meuPedido = new Pedido(1, LocalDate.now(), c1);
        meuPedido.addPizza("Frango");
        meuPedido.addPizza("Marguerita");
        todosOsPedidos.add(meuPedido);

        // Pedido 2
        Pedido meuPedido2 = new Pedido(2, LocalDate.now(), c2);
        meuPedido2.addPizza("Calabresa");
        meuPedido2.addPizza("Da casa");
        todosOsPedidos.add(meuPedido2);

        // Pedido 3
        Pedido meuPedido3 = new Pedido(3, LocalDate.now(), c3);
        meuPedido3.addPizza("Frango");
        meuPedido3.addPizza("Da casa");
        meuPedido3.addPizza("Da casa");
        todosOsPedidos.add(meuPedido3);

        // Pedido 4 (Vazio para teste)
        Pedido meuPedido4 = new Pedido(4, LocalDate.now(), c4);
        todosOsPedidos.add(meuPedido4);


        // --- PROCESSAMENTO INDIVIDUAL DE CADA PEDIDO NO LOOP ---
        System.out.println("\n--- INICIANDO PROCESSAMENTO DE TODOS OS PEDIDOS ---");

        for (Pedido pedidoAtual : todosOsPedidos) {
            System.out.println("\n----- Verificando Pedido Nº " + pedidoAtual.getNumero() + " -----");

            try {
                // 1. Lógica de negócio aplicada ANTES de finalizar
                pedidoAtual.aplicaPromocao();
                pedidoAtual.calculototal();
                pedidoAtual.mostradetalhes();

                // 2. Tenta finalizar o pedido. Se for vazio, lança exceção.
                pedidoAtual.finalizar();

                // 3. Se o pedido for VÁLIDO, ele será salvo no arquivo
                GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
                gerenciador.salvarPedido(pedidoAtual);

                // 4. Inicia o preparo em uma thread (supondo que PreparoPedido exista)
                PreparoPedido preparo = new PreparoPedido(pedidoAtual);
                Thread threadCozinha = new Thread(preparo);
                threadCozinha.start();
                System.out.println(">>> Pedido Nº " + pedidoAtual.getNumero() + " válido e enviado para preparo.");

            } catch (PedidoVazioException e) {
                // Se a exceção ocorrer, mostra o pop-up de erro
                JOptionPane.showMessageDialog(null, "Erro no Pedido Nº " + pedidoAtual.getNumero() + ": " + e.getMessage(), "Pedido Inválido", JOptionPane.WARNING_MESSAGE);
            }
        }

        System.out.println("\n-------------------------------------------------");
        System.out.println("Processamento de todos os pedidos finalizado.");
        System.out.println("Total de pedidos criados hoje: " + Pedido.getTotalPedidos());
    }
}