
package ggc.core;

import java.util.List;

public class BreakDownSale extends Sale {

    private List<Batch> _batch;

    BreakDownSale(int id, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
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

