package ggc.core;

import java.util.List;

public class Recipe {

    private double _alpha;
    private AggregateProduct _aggregateProduct;
    private List<Component> _recipeList;

    Recipe(double alpha, AggregateProduct aggregateProduct, List<Component> recipeList){
        _alpha = alpha;
        _aggregateProduct = aggregateProduct;
        _recipeList = recipeList;
    }

    @Override
    public String toString(){
        return "recipe";
    }

}
