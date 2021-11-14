package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.ProductDoesNotExistException;

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
    addIntegerField("date", Message.requestPaymentDeadline());
    addStringField("idProduct", Message.requestProductKey());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String idPartner = stringField("idPartner");
    String idProduct = stringField("idProduct");
    int date = integerField("date");
    int quantity = integerField("quantity");
    
    try {
      _receiver.addSaleByCredit(date, quantity, _receiver.getProduct(idProduct), _receiver.getPartner(idPartner));
    } catch(ProductDoesNotExistException pdne) {
      throw new UnknownProductKeyException(idProduct);
    } catch(PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(idPartner);
    } 
  }

}
