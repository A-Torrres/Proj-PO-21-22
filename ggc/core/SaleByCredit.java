
package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadLine;
    private double _amountPaid;
    private PaymentPeriod _paymentPeriod = PaymentPeriod.P1;

    SaleByCredit(int id, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(id, paymentD, baseValue, quant, prod, part);
        _deadLine = new Date(paymentD.getDay() + prod.getDeadLine());
    }

    /**
    * @return ?
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

}

enum PaymentPeriod {
    P1,
    P2,
    P3,
    P4
} 
