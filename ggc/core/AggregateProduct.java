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
        return getID() + "|" + Math.round(getMaxPrice()) + "|" + getTotalQuantity() + "|" + _recipe.toString();
    }
    
}
