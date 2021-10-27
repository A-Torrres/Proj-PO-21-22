package ggc.core;

import java.util.Iterator;
import java.util.List;

public class Recipe {

    private double _alpha;
    private AggregateProduct _aggregateProduct;
    private List<Component> _recipeList;

    //nao e possivel definir receitas cujos produtos nao sejam previamente conhecidos
    Recipe(double alpha, AggregateProduct aggrProduct, List<Component> recipeList) {
        _alpha = alpha;
        _aggregateProduct = aggrProduct;
        _recipeList = recipeList;
    }

    /**
   * @return agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n
   */
    @Override
    public String toString() {
        String components = "";
        Iterator<Component> it = _recipeList.iterator();
        
        while(true) {
            // nao estamos a verificar se existe o primeiro componente
            components += (it.next()).toString(); 
            if(it.hasNext()) {
                components += "#";
            }
            else break;
        }

        return Double.toString(_alpha) + "|" + components;
    }

}
