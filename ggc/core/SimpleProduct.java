package ggc.core;

public class SimpleProduct extends Product {
    
    SimpleProduct(String id, double price){
        super(id, price);
    }

    /**
   * @return idProduto|preço-máximo|stock-actual-total
   
    @Override
    public String toString() {
        return  getID() + "|" + 
                Math.round(getMaxPrice()) + "|" + 
                getTotalQuantity();
    }*/
    @Override
    public String toString() {
        return super.toString();
    }
}
