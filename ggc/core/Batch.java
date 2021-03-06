package ggc.core;

import java.io.Serializable;

public class Batch implements Serializable {

    private static final long serialVersionUID = 301486876482L;

    private double _pricePerUnit;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Batch(double price, int quantity, Product product, Partner partner) {
        _pricePerUnit = price;
        _quantity = quantity;
        _product = product;
        _partner = partner;
    }

    public double getPrice() {
        return _pricePerUnit;
    }

    public int getQuantity() {
        return _quantity;
    }
    
    public String getProductID() {
        return _product.getID();
    }

    public String getPartnerID() {
        return _partner.getID();
    }

    void addQuanity(int quantity) {
        _quantity += quantity;
    }

    void removeQuantity(int quantity) {
        _quantity -= quantity;
    }

    /**
   * @return idProduto|idParceiro|preço|stock-actual
   */
    @Override
    public String toString() {
        return  _product.getID() + "|" + 
                _partner.getID() + "|" +
                Math.round(_pricePerUnit) + "|" + 
                _quantity;
    }

}
