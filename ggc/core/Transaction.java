
package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    
    private static final long serialVersionUID = 30589876454L;
    
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Transaction(int id, Date paymentDate, double baseValue,  
                int quantity, Product product, Partner partner) {
        _id = id;
        _paymentDate = paymentDate;
        _baseValue = baseValue;
        _quantity = quantity;
        _product = product;
        _partner = partner;
    }

    int getID() {
        return _id;
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

    double getBaseValue() {
        return _baseValue;
    }

    int getQuantity() {
        return _quantity;
    }

    Partner getPartner() {
        return _partner;
    }
    
    abstract void pay(Date date);

    /**
   * @return true if paid, false otherwise.
   **/
    abstract boolean isPaid();

    @Override
    abstract public String toString();    

}
