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

import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
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
   * @throws ImportFileException
   */
  void importFile(String txtfile) throws ImportFileException {
    Parser parser = new Parser(this);
    try {
      parser.parseFile(txtfile);
    }
    catch (Exception e) {
      throw new ImportFileException();
    }
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
    
    _products.forEach( (id, product)-> batches.addAll(product.getBatches()));
    return batches;
  }

  /**
   * @param id a partner id.
   * @throws PartnerDoesNotExistException
   */
  Partner getPartner(String id) throws PartnerDoesNotExistException {
    Partner p = _partners.get(id.toUpperCase());

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
    String ID = id.toUpperCase();

    if(_partners.containsKey(ID))
        throw new PartnerKeyAlreadyExistException();
    _partners.put(ID, new Partner(id, name, address));
  }

  boolean existsProduct(String id) {
    return _products.containsKey(id.toUpperCase());
  }

  void addSimpleProduct(String id, double price) {
    _products.put(id.toUpperCase(), new SimpleProduct(id, price));
  }

  void addAggregateProduct(String id, AggregateProduct aggregateProduct) {
    _products.put(id.toUpperCase(), aggregateProduct);
  }

  Product getProduct(String id) throws ProductDoesNotExistException {
    Product p = _products.get(id.toUpperCase());
    if(p == null)
      throw new ProductDoesNotExistException();
    return p;
  }

}
