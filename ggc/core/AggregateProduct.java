package ggc.core;

public class AggregateProduct extends Product{
    
    private static final int DEADLINE = 3;
    private Recipe _recipe;
    

    AggregateProduct(String id, double price, Recipe recipe) {
            super(id, price);
            _recipe = recipe;
    }


    AggregateProduct(String id, double price){
        super(id, price);
    }

    @Override
    int getDeadLine() {
        return DEADLINE;
    }

    /**
     * AggregateProduct's recipe is immutable once declared
     */
    void addRecipe(Recipe recipe) {
        if(_recipe == null) {
            _recipe = recipe;
        }
    }

    /**
   * @return idProduto|preço-máximo|stock-actual-total|agravamento|
   *        componente-1:quantidade-1#...#componente-n:quantidade-n
   */
    @Override
    public String toString() {
        return  super.toString() + "|" + 
                _recipe.toString();
    }
    
}
