package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadLine;
    private double _amountPaid;
    private double _amountToPay;
    private PaymentPeriod _paymentPeriod;
    private boolean _paymentLate = false;

    SaleByCredit(int id, Date deadLine, Date currentDate, double baseValue, int quantity, Product product, Partner partner) {
        super(id, null, baseValue, quantity, product, partner);
        _deadLine = deadLine;
        _amountToPay = baseValue;
        updatePeriod(currentDate);
    }

    int updatePeriod(Date currentDate) {
        if(!isPaid()) {
            int diff = _deadLine.difference(currentDate);
            int prodDeadline = getProduct().getDeadLine();
            if(diff >= prodDeadline) {
                _paymentPeriod = PaymentPeriod.P1;
            }
            else if(0 <= diff && diff < prodDeadline) {
                _paymentPeriod = PaymentPeriod.P2;
            }
            else if(-prodDeadline <= diff && diff < 0) {
                _paymentPeriod = PaymentPeriod.P3;
            }
            else if(-prodDeadline > diff) {
                _paymentPeriod = PaymentPeriod.P4;
            }
            updateAccountingPrice(currentDate);
            return diff;
        }
        return 0;
    } 

    void updateAccountingPrice(Date currentDate) {
        double modifier = 1.0;
        PartnerState status = getPartner().getStatus();
        int diff = _deadLine.difference(currentDate);

        switch(_paymentPeriod) {
            case P1: modifier = status.getModifier(PaymentPeriod.P1, diff); break;
            case P2: modifier = status.getModifier(PaymentPeriod.P2, diff); break;
            case P3: modifier = status.getModifier(PaymentPeriod.P3, diff); break;
            case P4: modifier = status.getModifier(PaymentPeriod.P4, diff); break;
        }

        _amountToPay = getBaseValue() * modifier; 
    }

    boolean isLate() {
        return _paymentLate;
    }

    double getAcountingPrice() {
        return _amountToPay;
    }

    double getCurrentPrice() {
        return getAcountingPrice();
    }


    void setPaymentAsLate() {
        _paymentLate = true;
    }

    void pay(Date date) {
        setPaymentDate(date);
        _amountPaid = _amountToPay;

        if(!isLate()) 
            getPartner().addPoints(_amountPaid * 10);
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
            toReturn += "|" + getPaymentDate().getDay();
        
        return toReturn;
    }

}

enum PaymentPeriod {
    P1,
    P2,
    P3,
    P4
} 
