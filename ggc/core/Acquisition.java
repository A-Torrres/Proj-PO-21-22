
package ggc.core;

import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.ProductDoesNotExistException;

public class Acquisition extends Transaction {

    Acquisition(Warehouse warehouse, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(paymentD, baseValue, quant, prod, part);
        this.pay(warehouse, baseValue, quant, prod, part);
    }

    /**
   * @return ?
   */
    @Override
    public String toString() {
        return "";
    }

    public void pay(Warehouse warehouse, double pricePerUnit, int quantity, Product product, Partner partner) {
        Batch batch = new Batch(pricePerUnit, quantity, product, partner);
        try {
            warehouse.addBatch(product.getID(), batch);
            warehouse.changeBalance(-pricePerUnit*quantity);
        } catch (ProductDoesNotExistException pdne) {
            warehouse.addProduct(product.getID(), product);
            this.pay(warehouse, pricePerUnit, quantity, product, partner);
        }
    }

}
