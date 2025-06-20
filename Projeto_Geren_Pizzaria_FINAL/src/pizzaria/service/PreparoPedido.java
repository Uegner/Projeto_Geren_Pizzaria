package pizzaria.service;

import pizzaria.model.Pedido;
import pizzaria.model.Pizza; // Import necessário para acessar a classe Pizza

public class PreparoPedido implements Runnable {

    private final Pedido pedido;

    public PreparoPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        // Mensagem inicial para o pedido completo

        try {
            // Loop para simular o preparo de CADA pizza individualmente
            for (Pizza pizza : pedido.getPizzas()) {
                // Mensagem específica para a pizza atual

                // Pausa de 2.5 segundos para cada pizza
                Thread.sleep(5000);
            }

            // Mensagem final quando todas as pizzas do pedido estiverem prontas
            System.out.println("COZINHA: └─ ✅ Pedido de " + pedido.getCliente().getNome() + " está PRONTO! <<");

        } catch (InterruptedException e) {
            System.err.println("COZINHA: O preparo do pedido de " + pedido.getCliente().getNome() + " foi interrompido.");
            // Boa prática: restaura o status de interrupção da thread
            Thread.currentThread().interrupt();
        }
    }
}