package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Show all products.
 */
class DoShowAllProducts extends Command<WarehouseManager> {

  DoShowAllProducts(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws CommandException {

    //List<Product> products = new ArrayList<>(_receiver.getProducts());
    List<String> products = new ArrayList<>(_receiver.getProducts());
    Collections.sort(products, String.CASE_INSENSITIVE_ORDER);

    _display.popup(products);
  }

}
