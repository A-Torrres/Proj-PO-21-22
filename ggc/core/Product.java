package ggc.core;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 201589886410L; //?!?!

    private String _id;
    private double _maxPrice;
    private List<Batch> _batches;
    
    Product(String id){
        this(id, 0);
    }

    // temos de ter uma maneira de atualizar o maxPrice
    Product(String id, double price) {
        _id = id;
        if(_maxPrice < price)
            _maxPrice = price;
    }

    /**
     * @return the product's id.
     */
    String getID() {
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
        // se o batch for null quer dizer que atualmente 
        // nao existem quantidades no warehouse
        if(_batches.isEmpty())      
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
     * @return idProduto|preço-máximo|stock-actual-total
     */
    @Override
    public String toString() {
        return getID() + "|" + getMaxPrice() + "|" + getTotalQuantity();
    }

    void addBatch(double price, int quantity, Product product, Partner partner) {
        _batches.add(new Batch(price, quantity, product, partner));
    }
}
