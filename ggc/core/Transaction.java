
package ggc.core;

public abstract class Transaction {
    
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Transaction(int id, Date paymentD, double baseValue, int quant, Product prod, Partner partner) {
        _id = id;
        _paymentDate = paymentD;
        _baseValue = baseValue;
        _quantity = quant;
        _product = prod;
        _partner = partner;
    }

    /**
   * @return the transaction's paymentDate.
   */
    Date getPaymentDate() {
        return _paymentDate;
    }
    
    Product getProduct() {
        return _product;
    }
  
    /**
   * @return true if paid, false otherwise.
   *
    boolean isPaid() {
        return true;
    }

    /**
   * @return ?
   */
    @Override
    public String toString() {
        return  "" + "|" ;
    }

}