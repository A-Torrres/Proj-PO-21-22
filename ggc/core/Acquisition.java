package ggc.core;

public class Acquisition extends Transaction {

    Acquisition(int id, Date paymentDate, double price, 
                int quantity, Product product, Partner partner) {
        super(id, paymentDate, price, quantity, product, partner);
    }

    boolean isPaid() {
        return true;
    }

    void pay(Date date) {
        // Do nothing
    }

    double getCurrentPrice() {
        return getBaseValue();
    }


    // COMPRA|id|idParceiro|idProduto|quantidade|valor-pago|data-pagamento
    @Override
    public String toString() {
        return "COMPRA" + '|' +
                this.getID() + '|' +
                getPartner().getID() + '|' +
                getProduct().getID() +'|' +
                getQuantity() + '|' +
                Math.round(getBaseValue()) + '|' +
                getPaymentDate().getDay();
    }
    
}
