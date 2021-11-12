
package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable {

    private static final long serialVersionUID = 2193671278631L;
    private static int NEXT_ID = 0;
    
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Transaction(Date paymentD, double baseValue, int quant, Product prod, Partner partner) {
        _id = NEXT_ID++;
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

    int getID() {
        return _id;
    }
    
    //abstract void pay();

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

    public int getNextID() {
        return NEXT_ID;
    }

}