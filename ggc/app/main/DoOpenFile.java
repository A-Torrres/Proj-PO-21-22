package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

import ggc.app.exception.FileOpenFailedException;
import ggc.core.WarehouseManager;
import ggc.core.exception.UnavailableFileException;

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("file", Message.openFile());
  }

  @Override
  public final void execute() throws CommandException {
    String filename = stringField("file");
    try {
      _receiver.load(filename);
    } 
    catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    } 
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
  }

}
