package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("transactionID", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    int transactionID = integerField("transactionID");
    _display.popup(_receiver.getTransaction(transactionID).toString());
  }

}
