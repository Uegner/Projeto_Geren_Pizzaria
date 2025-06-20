package pizzaria.model;

public abstract class Pizza {

   private String nome;
   private double preco;
   private String ingredientes;

   public Pizza(String nome, double preco,  String ingredientes) {
       this.nome = nome;
       this.preco = preco;
       this.ingredientes = "";
   }

   public String getNome() {
       return this.nome;
   }

   public double getPreco() {
       return this.preco;
   }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getIngredientes() {
       return this.ingredientes;
   }

   public abstract void calcularPreco();

   //etapa 6
   public abstract String getTipo();


}
