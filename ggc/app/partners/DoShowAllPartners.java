package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ggc.core.Partner;
import ggc.core.WarehouseManager;

/**
 * Show all partners.
 */
class DoShowAllPartners extends Command<WarehouseManager> {

  DoShowAllPartners(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PARTNERS, receiver);
  }

  @Override
  public void execute() throws CommandException {
    List<String> partnersToString = new ArrayList<>();
    
    for(Partner p: _receiver.getPartners())
      partnersToString.add(p.toString());

    Collections.sort(partnersToString, String.CASE_INSENSITIVE_ORDER);
    _display.popup(partnersToString);
  }

}
