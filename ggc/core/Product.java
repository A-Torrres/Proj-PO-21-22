package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 2198371982732L;

    private String _id;
    private double _maxPrice;
    private Collection<Batch> _batches = new ArrayList<>();


    Product(String id, double price) {
        _id = id;
        _maxPrice = price;
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
    Collection<Batch> getBatches() {
        return _batches;
    }

/*
    /**
     * adds a new batch to the batches list
     *
    void addBatch(double price, int quantity, Product product, Partner partner) {
        _batches.add(new Batch(price, quantity, product, partner));
        if(_maxPrice < price)
            _maxPrice = price;
    }
*/
    /**
     * adds a new batch to the batches list
     */
    void addBatch(Batch batch, double price) {
        _batches.add(batch);
        if(_maxPrice < price)
            _maxPrice = price;
    }

/*
    @Override
    public abstract String toString();
*/
    @Override
    public String toString() {
        return getID() + "|" + 
        Math.round(getMaxPrice()) + "|" + 
        getTotalQuantity();
    }
    
}
