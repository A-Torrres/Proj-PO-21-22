package ggc.core.exception;

public class ProductDoesNotExistException extends Exception {
    
    private static final long serialVersionUID = 201585423820L;

    /**
     * Default constructor
     */
    public ProductDoesNotExistException() {
        // do nothing
      }

    /**
     * @param description
     */
    public ProductDoesNotExistException(String description) {
        super(description);
      }

}
