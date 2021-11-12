package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
  
  private double _balance;
  private Date _date = new Date();
  private Map<String, Product> _products = new HashMap<String, Product>();
  private Map<String, Partner> _partners = new HashMap<String, Partner>();
  private static int _nextTransactionId = 0;
  private Collection _transactions = new ArrayList<>();

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
    for (Partner partner : this.getPartners()) 
      partner.verifyPaymentPeriod(_date);
  }

  /**
   * @return warehouse's products.
   */
  Collection<Product> getProducts() {
    return _products.values();
  }

  /**
   * @param id a product id.
   * @return true if product exists, false otherwise.
   */
  boolean existsProduct(String id) {
    return _products.containsKey(id.toUpperCase());
  }

  /**
   * @param id a product id.
   * @param product the new product.
   */
  void addProduct(String id, Product product) {
    _products.put(id.toUpperCase(), product);
    _partners.forEach( (key, partner)-> product.addObserver(partner));
  }

  /**
   * @param id a product id.
   * @throws ProductDoesNotExistException
   * @return product with id id.
   */
  Product getProduct(String id) throws ProductDoesNotExistException {
    Product p = _products.get(id.toUpperCase());
    
    if(p == null)
      throw new ProductDoesNotExistException();
    return p;
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
   * @return partner's batches.
   */
  Collection<Batch> getBatchesByPartner(String id) throws PartnerDoesNotExistException {
    List<Batch> batches = new ArrayList<>();
    
    batches.addAll(getPartner(id).getBatches());
    return batches;
  }
  
  /**
   * @param id a product id.
   * @throws ProductDoesNotExistException
   * @return product's batches.
   */
  Collection<Batch> getBatchesByProduct(String id) throws ProductDoesNotExistException {
    List<Batch> batches = new ArrayList<>();
    
    batches.addAll(getProduct(id).getBatches());
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

  /**
   * @param id a partner id.
   * @param name the partner name.
   * @param adress the partner address.
   * @throws PartnerKeyAlreadyExistException
   */
  void addPartner(String id, String name, String address) throws PartnerKeyAlreadyExistException {
    String ID = id.toUpperCase();

    if(_partners.containsKey(ID))
      throw new PartnerKeyAlreadyExistException();
    
    Partner partner = new Partner(id, name, address);
    _partners.put(ID, partner);
    _products.forEach( (key, product)-> product.addObserver(partner));
  }

  void toggleNotifications(String idPartner, String idProduct) 
      throws PartnerDoesNotExistException, ProductDoesNotExistException {
    Partner partner = getPartner(idPartner);
    Product product = getProduct(idProduct);
    
    if(product.hasObserver(partner))
        product.removeObserver(partner);
    else product.addObserver(partner);
  }

  void clearNotifications(Partner partner) {
    partner.clearNotifications();
  }

}
