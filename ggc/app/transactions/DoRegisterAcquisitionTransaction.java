package ggc.app.transactions;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
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
    } catch(PartnerDoesNotExistException pdne) {
      throw new UnknownPartnerKeyException(idPartner);
    } catch(ProductDoesNotExistException pdne) {
      // Simple Product
      if(Form.requestString(Message.requestAddRecipe()).equals("n")) {  
        try {
          _receiver.registerSimpleProduct(idProduct, idPartner, price, quantity);
        } catch (Exception e) {
          //!Should not happen
        }
      }
      // Aggregate Product
      else {
        int amount = Form.requestInteger(Message.requestAmount());
        double alpha = Form.requestReal(Message.requestAlpha());
        List<String> componentIDs = new ArrayList<>();
        List<Integer> componentAmounts = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
          componentIDs.add(Form.requestString(Message.requestProductKey()));
          componentAmounts.add(Form.requestInteger(Message.requestAmount()));
        }
        try {
          _receiver.registerAggregateProduct(idProduct, idPartner, price, quantity, alpha, componentIDs, componentAmounts);
        } catch (ProductDoesNotExistException pdnee) {
          throw new UnknownProductKeyException(idProduct); //Caso algum componente não exista
        } catch (PartnerDoesNotExistException pdnee) {
          //!Should not happen
        }
      }
    }
  }

}
