package ggc.core;

public class Batch {

    private double _price;
    private int _quantity;
    private Product _product;
    private Partner _partner;


    public double getPrice() {
        return _price;
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

    /**
   * @return idProduto|idParceiro|preço|stock-actual
   */
    @Override
    public String toString() {
        return _product.getID() + "|" + _partner.getID() + "|" +
                Double.toString(_price) + "|" + Integer.toString(_quantity);
    }

}
