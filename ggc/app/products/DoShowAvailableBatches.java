package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ggc.core.Batch;
import ggc.core.WarehouseManager;

/**
 * Show available batches.
 */
class DoShowAvailableBatches extends Command<WarehouseManager> {

  DoShowAvailableBatches(WarehouseManager receiver) {
    super(Label.SHOW_AVAILABLE_BATCHES, receiver);
  }

  @Override
  public final void execute() throws CommandException {

    List<Batch> batches = new ArrayList<>(_receiver.getBatches());
    Collections.sort(batches, new Comp());

    _display.popup(batches);
  }

}

class Comp implements Comparator<Batch> {

  @Override
  public int compare(Batch b1, Batch b2) {
      int res;
      
      res = b1.getProductID().compareTo(b2.getProductID());
      if(res == 0) {
          res = b1.getPartnerID().compareTo(b2.getPartnerID());
          if(res == 0) {
            res = b1.getPrice() <  b2.getPrice() ? -1 : b1.getPrice() == b2.getPrice() ? 0 : 1;
            if(res == 0)
              res = b1.getQuantity() - b2.getQuantity();
          }
      }

     return res;
  }
}
