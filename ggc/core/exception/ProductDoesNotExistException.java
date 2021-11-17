package ggc.core.exception;

public class ProductDoesNotExistException extends Exception {
    
  private static final long serialVersionUID = 201585423820L;

  private String _id;
  /**
   * Default constructor
   */
  public ProductDoesNotExistException() {
    // do nothing
  }

  /**
   * @param description
   */
  public ProductDoesNotExistException(String id) {
    _id = id;
  }

  public String getID() {
    return _id;
  }

}
