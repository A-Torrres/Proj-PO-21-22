package ggc.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

import ggc.core.WarehouseManager;
import ggc.core.exception.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command and create a local Form

    if(_receiver.getFileName() != "") {
      try {
      _receiver.save();
      }
      catch (MissingFileAssociationException mfae) {
        //??? file does not exist?
      }
      catch (FileNotFoundException fnfe) {
        //???
      }
      catch (IOException ioe) {
        //???
      }
    }
  
    Form form = new Form();
    form.addStringField("file", Message.saveAs());
    String filename = form.stringField("file");

    try {
      _receiver.saveAs(filename);
    }
    catch (MissingFileAssociationException mfae) {
      //??? file does not exist?
    }
    catch (FileNotFoundException fnfe) {
      //???
    }
    catch (IOException ioe) {
      //???
    }
  }

}
