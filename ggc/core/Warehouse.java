package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.io.IOException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.NegativeDaysException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;
  
  // FIXME define attributes
  private Date _date = new Date();

  
  // FIXME define contructor(s)
  
  // FIXME define methods

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

  /**
   * 
   */
  int getCurrentDate() {
    return _date.getDay();
  }

  /**
   * 
   */
  void advanceDate(int days) throws NegativeDaysException{
    _date.add(days);
  }
}
