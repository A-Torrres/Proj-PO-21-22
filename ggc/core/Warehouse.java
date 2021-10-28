package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;

import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.app.exception.UnknownPartnerKeyException;
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
    _products = new HashSet<Product>();
    _partners = new HashSet<Partner>();
    //_transactions = new HashSet<>();
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
    List<String> productsToStrings = new ArrayList<>();
    for(Product p: _products)
      productsToStrings.add(p.toString());
    return productsToStrings;
  }

  Collection<String> getPartners() {
    List<String> partnersToStrings = new ArrayList<>();
    for(Partner p: _partners)
      partnersToStrings.add(p.toString());
    return partnersToStrings;
  }
  
  Partner getPartner(String iD) throws UnknownPartnerKeyException{
    for(Partner p: _partners) {
      if(p.getID() == iD) return p;
    }
    throw new UnknownPartnerKeyException(iD);
  }

  void registerPartner(String name, String add) throws DuplicatePartnerKeyException {
    if(_partners.contains()) { //criar PartnerHashSet para override equals() method do HashSet (probably same for product)
      throw new DuplicatePartnerKeyException(name);
    } else {
      _partners.add(new Partner(name, add));
    }
  }
}
