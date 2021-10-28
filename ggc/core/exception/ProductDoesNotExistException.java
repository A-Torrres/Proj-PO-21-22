package ggc.core.exception;

public class ProductDoesNotExistException extends Exception {
    
    private static final long serialVersionUID = 201586333310L; //temp UID

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
