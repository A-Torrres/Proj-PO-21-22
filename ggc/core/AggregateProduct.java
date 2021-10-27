package ggc.core;

public class AggregateProduct extends Product{
    
    private Recipe _recipe;

    AggregateProduct(String id){
        super(id);
    }
    
    AggregateProduct(String id, Recipe recipe){
        super(id);
        _recipe = recipe;
    }

    /**
   * @return idProduto|preço-máximo|stock-actual-total|agravamento|
   *        componente-1:quantidade-1#...#componente-n:quantidade-n
   */
    @Override
    public String toString() {
        return this.toString() + "|" + _recipe.toString();
    }
    
}
