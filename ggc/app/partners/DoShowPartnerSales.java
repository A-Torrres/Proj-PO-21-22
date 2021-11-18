package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Sale;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerSales extends Command<WarehouseManager> {

  DoShowPartnerSales(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_SALES, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    List<String> salesToString = new ArrayList<>();

    try {
      for(Sale sale : _receiver.getSalesByPartner(id)) {
        salesToString.add(sale.toString());
      }
    } 
    catch (PartnerDoesNotExistException pdnee) {
      throw new UnknownPartnerKeyException(id);
    }

    _display.popup(salesToString);
  }

}
