package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Partner;
import ggc.core.Sale;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.PartnerDoesNotExistException;

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("partnerID", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    try {
      Partner partner = _receiver.getPartner(stringField("partnerID"));
      for(Sale sale : partner.getSales()) {
        _display.addLine(sale.toString());
      }
      _display.display();
    } catch (PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(stringField("partnerID"));
    }
  }

}
