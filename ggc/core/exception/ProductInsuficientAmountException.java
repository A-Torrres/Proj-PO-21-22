package ggc.core.exception;

public class ProductInsuficientAmountException extends Exception {
    
    private static final long serialVersionUID = 201585423820L;
    
    private String _id;
    private int _quantityDesired;
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
    public ProductInsuficientAmountException(String id, int qD, int q) {
        _quantityAvailable = q;
        _quantityDesired = qD;
        _id = id;
    }

    public int getQuantityAvailable() {
        return _quantityAvailable;
    }
    
    public int getQuantityDesired() {
        return _quantityDesired;
    }
    
    public String getID() {
        return _id;
    }
    
}
