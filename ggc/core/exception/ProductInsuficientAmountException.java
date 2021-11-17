package ggc.core.exception;

public class ProductInsuficientAmountException extends Exception {
    
    private static final long serialVersionUID = 201585423820L;
    private int _quantityAvailable;
    /**
     * Default constructor
     */
    public ProductInsuficientAmountException() {
        // do nothing
    }
  
    /**
     * @param description
     */
    public ProductInsuficientAmountException(int quantity) {
        _quantityAvailable = quantity;
    }

    public int getQuantityAvailable() {
        return _quantityAvailable;
    }
    
}
