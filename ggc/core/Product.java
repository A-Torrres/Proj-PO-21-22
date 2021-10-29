package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 2198371982732L;

    private String _id;
    private double _maxPrice;
    private List<Batch> _batches;
    
    Product(String id){
        this(id, 0);
    }

    Product(String id, double price) {
        _id = id;
        _maxPrice = price;
        _batches = new ArrayList<>();
    }

    /**
     * @return the product's id.
     */
    public String getID() {
        return _id;
    }
    
    /**
     * @return the product's maxPrice.
     */
    double getMaxPrice() {
        return _maxPrice;
    }

    /**
     * @return int with quantity of product in the warehouse.
     */
    int getTotalQuantity() {
        int total = 0;
        if(!_batches.isEmpty())      
            for(Batch b: _batches)
                total += b.getQuantity();
        return total;
    }

    /**
     * @return the List of batches.
     */
    List<Batch> getBatches() {
        return _batches;
    }

    /**
     * adds a new batch to the batches list
     */
    void addBatch(double price, int quantity, Product product, Partner partner) {
        _batches.add(new Batch(price, quantity, product, partner));
    }

    @Override
    public abstract String toString();
    
}
