
package ggc.core;

public class Acquisition extends Transaction {

    Acquisition(int id, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(id, paymentD, baseValue, quant, prod, part);
    }

    /**
   * @return ?
   */
    @Override
    public String toString() {
        return "";
    }

}
