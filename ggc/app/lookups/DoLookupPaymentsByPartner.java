package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Transaction;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("idPartner");
    List<Transaction> payments;
    
    try {
      payments = new ArrayList<>(_receiver.getPaidTransactionsByPartner(id));
    } 
    catch (PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(id);
    }
    _display.popup(payments);
  }

}
