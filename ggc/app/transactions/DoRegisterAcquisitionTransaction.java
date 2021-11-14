package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.ProductDoesNotExistException;

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
    addStringField("idProduct", Message.requestProductKey());
    addRealField("price", Message.requestPrice());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String idPartner = stringField("idPartner");
    String idProduct = stringField("idProduct");
    double price = realField("price");
    int quantity = integerField("quantity");

    try {
      _receiver.registerAcquisition(idPartner, idProduct, price, quantity);
    }
    catch(PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(idPartner);
    } 
    catch(ProductDoesNotExistException pdne) {
      addStringField("key", Message.requestAddRecipe());

    }
  }

}
