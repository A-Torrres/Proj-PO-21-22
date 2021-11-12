package ggc.core;

import java.io.Serializable;

// notificacao por omissao
public class Notification implements Serializable {

    private static final long serialVersionUID = 1298312739089L;

    private String _type; // fazer Enum
    private Product _product;

    Notification(String type, Product prod) {
        _type = type;
        _product = prod;
    }
    
    /**
   * @return tipo-de-notificaçao|idProduto|preço-do-produto
   */
    @Override
    public String toString() {
          return _type + "|" + _product.getID() + "|" /*+ _product.getPrice()?? */;
    }
    
}

enum NotificationType {
    NEW{ public String toString(){ return "NEW";}},
    BARGAIN{ public String toString(){ return "BARGAIN";}}
}
