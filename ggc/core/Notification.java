package ggc.core;

import java.io.Serializable;

// notificacao por omissao
public class Notification implements Serializable {

    private static final long serialVersionUID = 1298312739089L;

    private NotificationType _type;
    private Product _product;
    private double _price;

    Notification(NotificationType type, Product product, double price) {
        _type = type;
        _product = product;
        _price = price;
    }
    
    /**
   * @return tipo-de-notificaçao|idProduto|preço-do-produto
   */
    @Override
    public String toString() {
          return _type + "|" + _product.getID() + "|" + Math.round(_price);
    }
    
}

enum NotificationType {
    NEW{ public String toString(){ return "NEW";}},
    BARGAIN{ public String toString(){ return "BARGAIN";}}
}
