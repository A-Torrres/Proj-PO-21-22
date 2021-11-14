package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Product implements Serializable, Observable {

    private static final long serialVersionUID = 2198371982732L;

    private String _id;
    private double _maxPrice;
    private Collection<Batch> _batches = new ArrayList<>();
    private Collection<Observer> _observers = new ArrayList<>();


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
    public double getMaxPrice() {
        return _maxPrice;
    }

    @Override
    public boolean addObserver(Observer obs) {
        return _observers.add(obs);
    }
    
    @Override
    public boolean removeObserver(Observer obs) {
        return _observers.remove(obs);
    }
    
    boolean hasObserver(Observer obs) {
        return _observers.contains(obs);
    }

    @Override
    public void notifyObservers(NotificationType type, double price) {
        for(Observer obs: _observers)
            obs.update(type, this, price);
    }

    abstract int getDeadLine();

    /**
     * @return int with quantity of product in the warehouse.
     */
    int getTotalQuantity() {
        int total = 0;     
        for(Batch b: _batches)
            total += b.getQuantity();
        return total;
    }

    double findMinPrice() {
        double min = _maxPrice;

        for(Batch b: _batches)
            if(min > b.getPrice())
                min = b.getPrice();
        return min;
    }

    /**
     * @return the List of batches.
     */
    Collection<Batch> getBatches() {
        return _batches;
    }

    /**
     * adds a new batch to the batches list
     */
    void addBatch(Batch batch, double price) {
        double minPrice = findMinPrice();

        _batches.add(batch); 
        
        if(_maxPrice < price)
            _maxPrice = price;
        
        if(minPrice > price)
            notifyObservers(NotificationType.BARGAIN, price);

        if(_batches.size() == 1)
            notifyObservers(NotificationType.NEW, price);
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
