package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
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

    for(Product p: _receiver.getProducts())
      productsToString.add(p.toString());

    Collections.sort(productsToString, String.CASE_INSENSITIVE_ORDER);
    _display.popup(productsToString);
  }

}
