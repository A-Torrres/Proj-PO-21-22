package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import ggc.core.exception.ProductInsuficientAmountException;
import ggc.core.exception.TransactionDoesNotExistException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  //private static final String List = null;
  
  private int NEXT_TRANSACTION_ID;
  private double _balance;
  private Date _date = new Date();
  private Map<String, Product> _products = new HashMap<String, Product>();
  private Map<String, Partner> _partners = new HashMap<String, Partner>();
  private Map<Integer, Transaction> _transactions = new HashMap<Integer, Transaction>();

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
    _partners.forEach( (key, partner)-> partner.clearNotifications());
  }

  /**
   * @return current Date.
   */
  Date getCurrentDate() {
    return _date;
  }

  /**
   * @param days number of days to advance current Date.
   * @throws NegativeDaysException
   */
  void advanceDate(int days) throws NegativeDaysException {
    _date.add(days);
    for (Partner partner : this.getPartners())
      partner.verifyLatePayments(_date);
  }

  double getBalance() {
    return _balance;
  }

  Transaction getTransaction(int id) throws TransactionDoesNotExistException {
    Transaction t = _transactions.get(id);
    
    if(t == null)
      throw new TransactionDoesNotExistException();
    return t;
  }

  void addTransaction(Transaction t) {
    _transactions.put(t.getID(), t);
  }

  void pay(int id) {
    Transaction transaction = _transactions.get(id);
    if(transaction instanceof SaleByCredit && !transaction.isPaid()) {
      transaction.pay(getCurrentDate());
    }
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

  void registerSimpleProduct(String idProduct, String idPartner, double price, int quantity) 
      throws PartnerDoesNotExistException, ProductDoesNotExistException {
    addProduct(idProduct, new SimpleProduct(idProduct));
    registerAcquisition(idPartner, idProduct, price, quantity);
  }

  void addBatch(String productID, Batch batch) throws ProductDoesNotExistException {
    try {
      this.getProduct(productID).addBatch(batch, batch.getPrice());
    } catch(ProductDoesNotExistException pdne) {
      throw pdne;
    }
  }

  void changeBalance(double money) {
    _balance += money;
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

  void registerAcquisition(String idPartner, String idProduct, double pricePU, int quantity) 
      throws PartnerDoesNotExistException, ProductDoesNotExistException {
    Partner partner = getPartner(idPartner);
    Product product = getProduct(idProduct);
    Acquisition acq = new Acquisition(NEXT_TRANSACTION_ID++, getCurrentDate(), pricePU * quantity, quantity, product, partner);
    Batch batch = new Batch(pricePU, quantity, product, partner);

    _balance -= pricePU * quantity;
    product.addBatch(batch, pricePU);
    partner.addBatch(batch);
    partner.addAcquisition(acq);
    addTransaction(acq);
    //_transactions.put(acq.getID(), acq);
  }

  void addSaleByCredit(String idPartner, int date, String idProduct, int quantity) 
      throws PartnerDoesNotExistException, ProductDoesNotExistException, ProductInsuficientAmountException {
    Partner partner = getPartner(idPartner);
    Product product = getProduct(idProduct);
    Date paymentDate = new Date(date);
    SaleByCredit sale;
    double baseValue = 0;
    List<Batch> batches;
    int quantityToGet = quantity;

    if(!product.checkQuantity(quantity))
        throw new ProductInsuficientAmountException(quantity);
    
    // retirar quantidade de produtos dos lotes e ir aumentando o baseValue
    batches = new ArrayList<>(product.getBatches());
    Collections.sort(batches, new BatchPriceComp());

    for(Batch b: batches) {
        int amount = b.getQuantity();
        int amountToConsum = quantityToGet;
        // se o batch atual n tiver suficiente tiramos tudo o q tem
        if((amount - quantityToGet) < 0)
          amountToConsum = amount;

        baseValue += b.getPrice() * amountToConsum;
        b.removeQuantity(amountToConsum);
        quantityToGet -= amountToConsum;

        if(quantityToGet == 0)
          break;
    }

    product.removeEmptyBatches();
    sale = new SaleByCredit(NEXT_TRANSACTION_ID++, paymentDate, baseValue, quantity, product, partner);
    _transactions.put(sale.getID(), sale);
    // atualizar _balance
  }

}

class BatchPriceComp implements Comparator<Batch> {

  @Override
  public int compare(Batch b1, Batch b2) {
    return  (int) (b1.getPrice() - b2.getPrice());
  }

}
