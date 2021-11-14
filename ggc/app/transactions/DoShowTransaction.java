package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.Transaction;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.TransactionDoesNotExistException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("idTransaction", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    int id = integerField("idTransaction");
    Transaction transaction;

    try{
      transaction = _receiver.getTransaction(id);
    }
    catch(TransactionDoesNotExistException tdnee) {
      throw new UnknownTransactionKeyException(id);
    }
    
    _display.popup(transaction.toString());
  }

}
