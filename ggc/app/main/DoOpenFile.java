package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.UnavailableFileException;

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {
    try {
      _receiver.load("test");
    } catch (ClassNotFoundException | UnavailableFileException | IOException e) {

    }
    /*try {
      _receiver.load("test");
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }*/
  }

}
