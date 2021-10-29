package ggc.core;

import java.io.Serializable;

public class Notification implements Serializable{

    private static final long serialVersionUID = 1298312739089L;

    private String _type; // fazer Enum
    private Product _product;
    //private boolean _notify;

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
