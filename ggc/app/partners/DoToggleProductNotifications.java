package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.ProductDoesNotExistException;
/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
    addStringField("idProduct", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    String idPartner = stringField("idPartner");
    String idProduct = stringField("idProduct");

    try {
      _receiver.toggleNotifications(idPartner, idProduct);
    }
    catch (PartnerDoesNotExistException pdnee) {
      throw new UnknownPartnerKeyException(idPartner);
    }
    catch (ProductDoesNotExistException pdnee) {
      throw new UnknownProductKeyException(idProduct);
    }
  }

}
