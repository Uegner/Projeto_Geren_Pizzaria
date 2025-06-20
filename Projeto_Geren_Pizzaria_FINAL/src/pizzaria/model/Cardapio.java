package pizzaria.model;
import java.util.ArrayList;

public class Cardapio {

    public static ArrayList<Pizza> pizzasDisponiveis =  new ArrayList<>();

    //etapa bonus para buscar as pizzas pelo cardapio
    public static Pizza buscarPizza(String nome){
        for (Pizza pizza : pizzasDisponiveis) {
            if (pizza.getNome().equalsIgnoreCase(nome)) {
                return pizza;
            }
        }
        return null;
    }
}
