package ggc.core.exception;

/**
 * Class for representing a date error. Adding negative days to the current date.
 */
public class NegativeDaysException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201708301010L; //????
  
    /**
     * Default constructor
     */
    public NegativeDaysException() {
      // do nothing
    }
  
    /**
     * @param description
     */
    public NegativeDaysException(String description) {
      super(description);
    }
}
