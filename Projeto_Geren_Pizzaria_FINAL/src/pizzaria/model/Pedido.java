package pizzaria.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private int numero;
    private LocalDate data = LocalDate.now();
    private double ValorTotalPedido;
    private double totalVendasDia;

    public Pedido(int numero, LocalDate data, Cliente cliente) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        totalPedidos++;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getData() {
        return data;
    }

    //etapa 7
    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
    //fim da etapa 7

    //etapa 2, esses dois elementos foram adicionados no construtor
    private Cliente cliente;
    private ArrayList<Pizza>  pizzas;

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public void aplicaPromocao(){
        System.out.println("Verificando as promoções disponíveis para o pedido " + this.numero + "...");

        for (Pizza pizza : this.pizzas){
            if (pizza instanceof Promocional){
                Promocional pizzaPromo = (Promocional) pizza;
                pizzaPromo.desconto(10);
            }
        }
    }


    //etapa 4
    public double calculototal(){


        double total = 0;

        for (Pizza pizza : this.pizzas) {
            total += pizza.getPreco();
        }
        this.ValorTotalPedido = total;

        totalVendasDia += total;

        return total;
    }

    public double getValorTotalPedido() {
        return ValorTotalPedido;
    }
    //fim da etapa 4

    public void mostradetalhes(){
        System.out.println("\n==== Detalhes do Pedido ====");
        System.out.println("Cliente: " + this.cliente.getNome());

        for (Pizza pizza : this.pizzas) {
            System.out.println("Pizza: " + pizza.getNome() + " || Preço: R$" +pizza.getPreco());
        }

        System.out.printf("Total do Pedido: R$ %.2f%n", ValorTotalPedido);
        System.out.println("==========================================\n");

        System.out.println("\n");
    }
    //fim da etapa 2

    //etapa 5
    private static int totalPedidos = 0;

    public static int getTotalPedidos(){
        return totalPedidos;
    }
    //fim da etapa 5

    //etapa 6
    public void finalizar() throws PedidoVazioException{
        if(this.pizzas.isEmpty()){
            throw new PedidoVazioException("Nenhuma pizza encontrada no pedido " + this.numero);
        } else{
            System.out.println("==== Finalizando pedido " + this.numero + " ====");
        }
    }
    //fim da etapa 6

    //etapa bonus: deixar mais eficiente a questão cardapio
    public void addPizza(String nomePizza){
        Pizza pizzaEncontrada = Cardapio.buscarPizza(nomePizza);

        if(pizzaEncontrada != null){
            this.addPizza(pizzaEncontrada);
            System.out.println("Pizza '" + nomePizza + "' adicionada no pedido " + this.numero);
        } else {
            System.out.println("A pizza '" + nomePizza + "' não foi encontrada no cardápio e não foi adicionada.");
        }
    }

    //etapa bonus: deixar mais eficiente a questão cardapio

}
