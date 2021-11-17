
package ggc.core;

public abstract class Sale extends Transaction {

    private boolean _payed = false;

    Sale(int id, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(id, paymentD, baseValue, quant, prod, part);
    }

    Product getProduct() {
        return super.getProduct();
    }

    boolean isPaid() {
        return _payed;
    }

    abstract int updatePeriod(Date date);

}
