
package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadLine;
    private double _amountPaid;
    private PaymentPeriod _paymentPeriod = PaymentPeriod.P1;

    SaleByCredit(Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(paymentD, baseValue, quant, prod, part);
        _deadLine = new Date(paymentD.getDay() + prod.getDeadLine());
    }

    /**
    * @return 
    */
    @Override
    public String toString() {
        return "";
    }  

    int updatePeriod(Date currentDate) {
        if(_amountPaid == 0) {
            int diff = _deadLine.difference(currentDate);
            Product prod = super.getProduct();
            if(diff >= prod.getDeadLine()) {
                _paymentPeriod = PaymentPeriod.P1;
            }
            if(0 <= diff && diff < prod.getDeadLine()) {
                _paymentPeriod = PaymentPeriod.P2;
            }
            if(-prod.getDeadLine() <= diff && diff < 0) {
                _paymentPeriod = PaymentPeriod.P3;
            }
            if(-prod.getDeadLine() > diff) {
                _paymentPeriod = PaymentPeriod.P4;
            }
            return diff;
        }
        return 0;
    }

    double getActualTotalPrice(Date currentDate) {
        double modifier = 1.0;
        PartnerState status = this.getPartner().getStatus();

        switch(_paymentPeriod) {
            case P1: modifier = status.getModifier(PaymentPeriod.P1, _deadLine.difference(currentDate));
            case P2: modifier = status.getModifier(PaymentPeriod.P2, _deadLine.difference(currentDate));
            case P3: modifier = status.getModifier(PaymentPeriod.P3, _deadLine.difference(currentDate));
            case P4: modifier = status.getModifier(PaymentPeriod.P4, _deadLine.difference(currentDate));
        }

        return this.getBaseValue() * this.getQuantity() * modifier;
    }

    @Override
    boolean isPaid() {
        return _amountPaid != 0;
    }

    void pay(Date date) {
        _amountPaid = getActualTotalPrice(date);
    }

}

enum PaymentPeriod {
    P1,
    P2,
    P3,
    P4
} 
