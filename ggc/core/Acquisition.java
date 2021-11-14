package ggc.core;

public class Acquisition extends Transaction {

    

    Acquisition(Date paymentDate, double baseValue, int quant, Product product, Partner partner) {
        super(paymentDate, baseValue, quant, product, partner);
    }

    // COMPRA|id|idParceiro|idProduto|quantidade|valor-pago|data-pagamento
    @Override
    public String toString() {
        return "COMPRA" + '|' +
                this.getID() + '|' +
                getPartner().getID() + '|' +
                getProduct().getID() +'|' +
                getQuantity() + '|' +
                getBaseValue() + '|' +
                getPaymentDate().getDay();
    }
    
}
