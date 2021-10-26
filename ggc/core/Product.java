package ggc.core;

import java.io.Serializable;

public abstract class Product implements Serializable{

    private static final long serialVersionUID = 201589886410L; //?!?!

    private String _id;
    private double _maxPrice;
    //private Batch _batch;
    
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

    @Override
    public String toString() {
        return _id;
    }

}
