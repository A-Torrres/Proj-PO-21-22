package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ggc.core.WarehouseManager;
import ggc.core.Product;

/**
 * Show all products.
 */
class DoShowAllProducts extends Command<WarehouseManager> {

  DoShowAllProducts(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    List<String> productsToString = new ArrayList<>();
    List<Product> products = new ArrayList<>(_receiver.getProducts());
    //Collections.sort(products, Comparator.comparing(Product :: getID));

    for(Product p: products)
      productsToString.add(p.toString());

    Collections.sort(productsToString, String.CASE_INSENSITIVE_ORDER);
    
    _display.popup(productsToString);
  }

}
