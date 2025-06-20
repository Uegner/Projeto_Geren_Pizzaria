package pizzaria.model;//classe criada da etapa 3

public class PizzaEspecial extends Pizza {

    public PizzaEspecial(String nome, double preco, String ingredientes) {
        super(nome, preco, ingredientes);
    }

    @Override
    public void calcularPreco() {
        double novoPreco = getPreco() * 1.15;
        setPreco(novoPreco);
        System.out.println("Calculando preço da Pizza Especial: Acréscimo de 15% aplicado.");
    }

    //etapa 6
    @Override
    public String getTipo() {
        return "Especial";
    }
}
