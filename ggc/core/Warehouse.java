package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import ggc.app.exception.UnknownProductKeyException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.NegativeDaysException;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.PartnerKeyAlreadyExistException;
import ggc.core.exception.ProductDoesNotExistException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private static final String List = null;
  
  private Date _date;
  private Map<String, Product> _products;
  private Map<String, Partner> _partners;

  
  // faco as atribuicoes quando declaro as variaveis ou aqui no construtor?
  Warehouse() {
    _date = new Date();
    _products = new HashMap<String, Product>();
    _partners = new HashMap<String, Partner>();
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   * @throws PartnerKeyAlreadyExistException
   * @throws PartnerDoesNotExistException
   * @throws ProductDoesNotExistException
   */
  void importFile(String txtfile) throws IOException, BadEntryException, ProductDoesNotExistException, PartnerDoesNotExistException, PartnerKeyAlreadyExistException {
    Parser parser = new Parser(this);
    parser.parseFile(txtfile);
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
    return _products.values();
  }

  /**
   * @return warehouse's current batches.
   */
  Collection<Batch> getBatches() {
    List<Batch> batches = new ArrayList<>();
    
    _products.forEach(
      (id, product) -> batches.addAll(product.getBatches()));
    
    return Collections.unmodifiableCollection(batches);
  }

  /**
   * @param id a partner id.
   * @throws PartnerDoesNotExistException
   */
  Partner getPartner(String id) throws PartnerDoesNotExistException {
    Partner p = _partners.get(id);
    
    if(p == null)
      throw new PartnerDoesNotExistException(id);

    return p;
  }

  /**
   * @return warehouse's partners.
   */
  Collection<Partner> getPartners() {
    return _partners.values();
  }

  void addPartner(String id, String name, String address) 
      throws PartnerKeyAlreadyExistException {
    
    if(_partners.containsKey(id))
        throw new PartnerKeyAlreadyExistException();

    _partners.put(id, new Partner(id, name, address));
  }

  boolean existsProduct(String id) {
    return _products.containsKey(id);
  }

  void addSimpleProduct(String id, double price) {
    _products.put(id, new SimpleProduct(id)); //add price to constructor
  }

  void addSimpleProduct(String id) {
    this.addSimpleProduct(id, 0.0);
  }

  Product getProduct(String id) throws ProductDoesNotExistException {
    if(_products.containsKey(id))
      return _products.get(id);
    else throw new ProductDoesNotExistException();
  }

  void addAggregateProduct(String id, AggregateProduct aggregateProduct) {
    _products.put(id, aggregateProduct);
  }
}
