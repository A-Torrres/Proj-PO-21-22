package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnavailableProductException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.ProductDoesNotExistException;
import ggc.core.exception.ProductInsuficientAmountException;

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
    addStringField("idProduct", Message.requestProductKey());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String idPartner = stringField("idPartner");
    String idProduct = stringField("idProduct");
    int quantity = integerField("quantity");

    try {
      _receiver.registerBreakdown(idPartner, idProduct, quantity);
    } 
    catch(PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(idPartner);
    }
    catch(ProductDoesNotExistException pdne) {
      throw new UnknownProductKeyException(idProduct);
    } 
    catch (ProductInsuficientAmountException piae) {
      throw new UnavailableProductException(piae.getID(), piae.getQuantityDesired(), piae.getQuantityAvailable());
    }
    
  }

}
