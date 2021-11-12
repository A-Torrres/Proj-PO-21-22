package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Notification;
import ggc.core.Partner;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    Partner partner;
    List<String> notifications = new ArrayList<>();

    try {
      partner = _receiver.getPartner(id);
    } 
    catch (PartnerDoesNotExistException pdnee) {
      throw new UnknownPartnerKeyException(id);
    }

    for(Notification n: partner.getPartnerNotifications())
      notifications.add(n.toString());

    _display.popup(partner.toString());
    _display.popup(notifications);
    _receiver.clearNotifications(partner);
  }

}
