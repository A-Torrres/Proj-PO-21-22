package ggc.core;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 201589886410L; //?!?!

    private String _id;
    private double _maxPrice;
    private List<Batch> _batch;
    
    // temos de ter uma maneira de atualizar o maxPrice
    Product(String id){
        this(id, 0);
    }
    
    Product(String id, double maxPrice){
        _id = id;
        _maxPrice = maxPrice;
    }

    /**
   * @return the product's id.
   */
    String getID(){
        return _id;
    }
    
    /**
   * @return the product's maxPrice.
   */
    double getMaxPrice(){
        return _maxPrice;
    }

    int getTotalQuantity() {
        int total = 0;
        // se o batch for null quer dizer que atualmente 
        // nao existem quantidades no warehouse
        if(_batch != null)      
            for(Batch b: _batch)
                total += b.getQuantity();
        return total;
    }

    /*
    boolean abstract checkQuantity(int quant, Partner part) {
    }
    */
    
    @Override
    public String toString() {
        return _id;
    }

}
