package ggc.core;

public class Component {
    
    private int _quantity;
    private Product _product;

    Component(int quant, Product prod) {
        _quantity = quant;
        _product = prod;
    }

    /**
   * @return componente:quantidade
   */
  @Override
  public String toString() {
      return _product.getID() + "|" + Integer.toString(_quantity);
  }

}
