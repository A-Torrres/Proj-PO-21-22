package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ggc.app.exception.UnknownProductKeyException;
import ggc.core.Batch;
import ggc.core.WarehouseManager;
import ggc.core.exception.ProductDoesNotExistException;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("id", Message.requestProductKey());
  }

  @Override
  public final void execute() throws CommandException {
    String id = stringField("id");
    List<Batch> batches;

    try {
      batches = new ArrayList<>(_receiver.getBatchesByProduct(id));
    } 
    catch (ProductDoesNotExistException pdnee) {
      throw new UnknownProductKeyException(id);
    }

    Collections.sort(batches, new Comp());
    _display.popup(batches);
  }

}
