package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadLine;
    private double _amountPaid;
    private double _amountToPay;
    private PaymentPeriod _paymentPeriod = PaymentPeriod.P1;

    SaleByCredit(int id, Date date, double baseValue, int quantity, Product product, Partner partner) {
        super(id, date, baseValue, quantity, product, partner);
        _deadLine = date;
        _amountToPay = baseValue;
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

    double getAcountingPrice(Date currentDate) {
        double modifier = 1.0;
        PartnerState status = getPartner().getStatus();

        switch(_paymentPeriod) {
            case P1: modifier = status.getModifier(PaymentPeriod.P1, _deadLine.difference(currentDate));
            case P2: modifier = status.getModifier(PaymentPeriod.P2, _deadLine.difference(currentDate));
            case P3: modifier = status.getModifier(PaymentPeriod.P3, _deadLine.difference(currentDate));
            case P4: modifier = status.getModifier(PaymentPeriod.P4, _deadLine.difference(currentDate));
        }

        return getBaseValue() * modifier;
    }

    void pay(Date date) {
        _amountPaid = getAcountingPrice(date);
    }

    boolean isPaid() {
        return _amountPaid != 0;
    }

    // VENDA|id|idParceiro|idProduto|quantidade|valor-base|valor-a-pagamento|data-limite|data-pagamento
    @Override
    public String toString() {
        String toReturn = "VENDA" + '|' +
                            getID() + '|' +
                            getPartner().getID() + '|' +
                            getProduct().getID() + '|' +
                            getQuantity() + '|' +
                            Math.round(getBaseValue())  + '|' +
                            Math.round(_amountToPay) + '|' +
                            _deadLine.getDay();
        if(isPaid())
            toReturn += getPaymentDate().getDay();
        
        return toReturn;
    }

}

enum PaymentPeriod {
    P1,
    P2,
    P3,
    P4
} 
