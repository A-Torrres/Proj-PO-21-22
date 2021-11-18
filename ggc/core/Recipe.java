package ggc.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Recipe implements Serializable {

    private static final long serialVersionUID = 20938189723908L;

    private double _alpha;
    private AggregateProduct _aggregateProduct;
    private Collection<Component> _recipeList;

    Recipe(double alpha, AggregateProduct aggrProduct, List<Component> recipeList) {
        _alpha = alpha;
        _aggregateProduct = aggrProduct;
        _recipeList = recipeList;
    }

    AggregateProduct getAggregateProduct() {
        return _aggregateProduct;
    }

    double getAlpha() {
        return _alpha;
    }

    /**
   * @return agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n
   */
    @Override
    public String toString() {
        String components = "";
        Iterator<Component> it = _recipeList.iterator();
        
        while(it.hasNext()) {
            components += (it.next()).toString(); 
            if(it.hasNext())
                components += "#";
        }
        return components;
    }

}
