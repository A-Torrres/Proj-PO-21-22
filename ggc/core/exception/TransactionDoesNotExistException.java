package ggc.core.exception;

public class TransactionDoesNotExistException extends Exception {
    
    private static final long serialVersionUID = 20447556423820L;

    /**
     * Default constructor
     */
    public TransactionDoesNotExistException() {
        // do nothing
      }

    /**
     * @param description
     */
    public TransactionDoesNotExistException(String description) {
        super(description);
      }

}
