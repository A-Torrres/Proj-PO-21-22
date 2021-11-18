package ggc.core;

public class SimpleProduct extends Product {
    
    private final int DEADLINE = 5;

    SimpleProduct(String id){
        super(id);
    }

    @Override
    int getDeadLine() {
        return DEADLINE;
    }

    /**
    * @return idProduto|preço-máximo|stock-actual-total
    */
    @Override
    public String toString() {
        return super.toString();
    }
}
