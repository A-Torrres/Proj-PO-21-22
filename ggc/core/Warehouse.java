package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.NegativeDaysException;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.PartnerKeyAlreadyExistException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private static final String List = null;
  
  private Date _date;
  private Set<Product> _products;
  private Map<String, Partner> _partners;
  //private Set<Transaction> _transaction;

  
  // faco as atribuicoes quando declaro as variaveis ou aqui no construtor?
  Warehouse() {
    _date = new Date();
    _products = new HashSet<>();
    _partners = new HashMap<String, Partner>();
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
   * @return warehouse's products.
   */
  Collection<Product> getProducts() {
    return _products;
  }

  /**
   * @return warehouse's current batches.
   */
  Collection<Batch> getBatches() {
    List<Batch> batches = new ArrayList<>();
    
    for(Product p: _products)
      batches.addAll(p.getBatches());
    
    return batches;
  }

  /**
   * @param id a partner id.
   * @throws PartnerDoesNotExistException
   */
  Partner getPartner(String id) throws PartnerDoesNotExistException {
    Partner p = _partners.get(id);
    
    if(p == null)
      throw new PartnerDoesNotExistException();

    return p;
  }

  /**
   * @return warehouse's partners.
   */
  Map<String, Partner> getPartners() {
    return _partners;
  }

  void addPartner(String id, String name, String address) 
      throws PartnerKeyAlreadyExistException {
    
    if(_partners.containsKey(id))
        throw new PartnerKeyAlreadyExistException();

    _partners.put(id, new Partner(id, name, address));
  }

}
