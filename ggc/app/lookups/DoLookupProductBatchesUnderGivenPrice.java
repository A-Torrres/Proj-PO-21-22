package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ggc.core.Batch;
import ggc.core.WarehouseManager;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {

  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    addRealField("price", Message.requestPriceLimit());
  }

  @Override
  public void execute() throws CommandException {
    Double price = realField("price");

    List<Batch> batches = new ArrayList<>(_receiver.getBatches());
    batches.stream().filter(b -> b.getPrice() < price);
    Collections.sort(batches, new BatchStringComp());

    _display.popup(batches);
  }

}


class BatchStringComp implements Comparator<Batch> {

  @Override
  public int compare(Batch b1, Batch b2) {
    
      int res = b1.getProductID().compareTo(b2.getProductID());
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