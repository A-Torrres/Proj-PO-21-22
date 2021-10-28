package ggc.core.exception;

/**
 * Class for representing a partner error. The partner's id already exist.
 */
public class PartnerKeyAlreadyExistException extends Exception {
    
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201586335710L;

    /**
     * Default constructor
     */
    public PartnerKeyAlreadyExistException() {
        // do nothing
      }

    /**
     * @param description
     */
    public PartnerKeyAlreadyExistException(String description) {
        super(description);
      }

}
