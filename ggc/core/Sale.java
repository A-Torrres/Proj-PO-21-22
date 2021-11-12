
package ggc.core;

public abstract class Sale extends Transaction {

    Sale(Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(paymentD, baseValue, quant, prod, part);
    }

    Product getProduct() {
        return super.getProduct();
    }

    abstract int updatePeriod(Date date);

}