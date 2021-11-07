package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Batch;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    String id = stringField("id");
    List<Batch> batches;

    try {
      batches = new ArrayList<>(_receiver.getBatchesByPartner(id));
    } 
    catch (PartnerDoesNotExistException pdnee) {
      throw new UnknownPartnerKeyException(id);
    }

    Collections.sort(batches, new Comp());
    _display.popup(batches);
  }

}
