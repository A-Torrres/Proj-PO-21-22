package ggc.core;

import java.io.Serializable;

public class Component implements Serializable {

    private static final long serialVersionUID = 123718924781L;
    
    private int _quantity;
    private Product _product;

    Component(int quant, Product prod) {
        _quantity = quant;
        _product = prod;
    }

    public int getQuantity() {
        return _quantity;
    }

    /**
    * @return componente:quantidade
    */
    @Override
    public String toString() {
        return _product.getID() + ":" + _quantity;
    }

}
