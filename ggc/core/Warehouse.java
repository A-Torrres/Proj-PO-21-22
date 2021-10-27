package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.NegativeDaysException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private static final String List = null;
  
  private Date _date;
  private Set<Product> _products;
  private Set<Partner> _partners;
  //private Set<Transaction> _transaction;

  
  // faco as atribuicoes quando declaro as variaveis ou aqui no construtor?
  Warehouse() {
    _date = new Date();
    _products = new HashSet<>();
    _partners = new HashSet<>();
    //_transaction = new HashSet<>();
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

  /**
   * @return current Date.
   */
  int getCurrentDate() {
    return _date.getDay();
  }

  /**
   * @param days number of days to advance current Date.
   * @throws NegativeDaysException
   */
  void advanceDate(int days) throws NegativeDaysException {
    _date.add(days);
  }

  /**
   * @param days number of days to advance current Date.
   * @throws NegativeDaysException
   */
  Collection<String> getProducts() {
    List<String> strProd = new ArrayList<>();
    for(Product p: _products)
      strProd.add(p.toString());
    return strProd;
  }

}
