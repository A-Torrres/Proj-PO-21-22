
package ggc.core;

import java.util.ArrayList;
import java.util.Collection;

public class BreakDownSale extends Sale {

    private double _valuePaid;
    private Collection<Batch> _batches;

    BreakDownSale(int id, Date paymentDate, double baseValue, int quantity, 
                    Product product, Partner partner, Collection<Batch> batches) {
        
        super(id, paymentDate, baseValue, quantity, product, partner);
        _valuePaid = Math.abs(baseValue);
        _batches = new ArrayList<>(batches);
    }

    double getCurrentPrice() {
        return getBaseValue();
    }

    boolean isPaid() {
        return true;
    }

    boolean isLate() {
        return false;
    }

    void pay(Date date) {
        // Do nothing
    }

    void setPaymentAsLate() {
        //Do nothing
    }

    int updatePeriod(Date date) {
        return 0; 
    }

    /**
     * @return DESAGREGAÇÃO|id|idParceiro|idProduto|quantidade|valor-base|valor-pago|data-pagamento|idC1:q1:v1#...#idCN:qN:vN
     */
    @Override
    public String toString() {
        return "DESAGREGAÇÃO" + '|' +
                getID() + '|' +
                getPartner().getID() + '|' +
                getProduct().getID() + '|' +
                getQuantity() + '|' +
                Math.round(getBaseValue())  + '|' +
                Math.round(_valuePaid) + '|' +
                getPaymentDate() + '|' +
                "idC1:q1:v1#...#idCN:qN:vN";
    }

}

