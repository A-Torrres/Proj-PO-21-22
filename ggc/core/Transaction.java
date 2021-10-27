package ggc.core;

public abstract class Transaction {
    
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;

    Transaction(int id, Date paymentD, double baseValue, int quant, Product prod) {
        _id = id;
        _paymentDate = paymentD;
        _baseValue = baseValue;
        _quantity = quant;
        _product = prod;
    }

    /**
   * @return the transaction's paymentDate.
   */
    Date getPaymentDate() {
        return _paymentDate;
    }
  
    /**
   * @return true if paid, false otherwise.
   */
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
