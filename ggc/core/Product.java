package ggc.core;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 201589886410L; //?!?!

    private String _id;
    private double _maxPrice;
    private List<Batch> _batch;
    
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
    
    /**
     * @return idProduto|preço-máximo|stock-actual-total
     */
    @Override
    public String toString() {
        return getID() + "|" + getMaxPrice() + "|" + 
                Integer.toString(getTotalQuantity());
    }

}
