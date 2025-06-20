package pizzaria.model;//classe criada da etapa 3

public class PizzaTradicional extends Pizza implements Promocional {

    public PizzaTradicional(String nome, double preco, String ingredientes) {
        super(nome, preco, ingredientes);
    }

    @Override
    public void calcularPreco() {
        System.out.println("Calculando preço da Pizza Tradicional: Sem acréscimos.");
    }

    @Override
    public void desconto(int percentual) {
        double precoinicial = getPreco();
        double desconto = getPreco() * (percentual / 100.0);
        double precofinal = precoinicial - desconto;

        setPreco(precofinal);

        System.out.println("Desconto de " + percentual + "% aplicado na " + getNome());
    }

    //etapa 6
    @Override
    public String getTipo() {
        return "Tradicional";
    }
}
