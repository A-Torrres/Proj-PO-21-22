package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.TransactionDoesNotExistException;

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    addIntegerField("idTransaction", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    int id = integerField("idTransaction");

    try {
      _receiver.pay(id);
    }
    catch(TransactionDoesNotExistException tdnee) {
      throw new UnknownTransactionKeyException(id);
    }
  }

}
