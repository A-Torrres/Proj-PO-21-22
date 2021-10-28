package ggc.core.exception;

/**
 * Class for representing a partner error. The partner's id doesn't exist.
 */
public class PartnerDoesNotExistException extends Exception {
    
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201586333310L;

    /**
     * Default constructor
     */
    public PartnerDoesNotExistException() {
        // do nothing
      }

    /**
     * @param description
     */
    public PartnerDoesNotExistException(String description) {
        super(description);
      }

}
