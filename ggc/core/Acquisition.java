
package ggc.core;

import ggc.core.exception.ProductDoesNotExistException;

public class Acquisition extends Transaction {

    Acquisition(Warehouse warehouse, Date paymentD, double baseValue, int quant, Product prod, Partner part) throws ProductDoesNotExistException {
        super(paymentD, baseValue, quant, prod, part);
        this.pay(warehouse, baseValue, quant, prod, part);
    }

    /**
   * @return ?
   */
    @Override
    public String toString() {
        return "COMPRA" +
                getID() + 
                getPartner().getID() + 
                getProduct().getID() +
                getQuantity() + 
                getBaseValue() + 
                getPaymentDate().getDay();
    }

    void pay(Warehouse warehouse, double pricePerUnit, int quantity, Product product, Partner partner) throws ProductDoesNotExistException {
        Batch batch = new Batch(pricePerUnit, quantity, product, partner);

        warehouse.addBatch(product.getID(), batch);
        warehouse.changeBalance(-pricePerUnit*quantity);
    }
}
