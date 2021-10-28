package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.io.IOException;
import java.io.FileNotFoundException;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;
import ggc.core.exception.NegativeDaysException;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.PartnerKeyAlreadyExistException;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The wharehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile, e);
    }
  }

  /**
   * 
   */
  public int getCurrentDate() {
    return _warehouse.getCurrentDate();
  }
  
  /**
   * 
   */
  public void advanceDate(int days) throws NegativeDaysException {
    _warehouse.advanceDate(days);
  }

  /**
   * 
   */
  public Collection<Product> getProducts() {
    return _warehouse.getProducts();
  }

  /**
   * 
   */
  public Collection<Batch> getBatches() {
    return _warehouse.getBatches();
  }

  public Partner getPartner(String id) throws PartnerDoesNotExistException {
    return _warehouse.getPartner(id);
  }

  public Map<String, Partner> getPartners() {
    return _warehouse.getPartners();
  }

  public void addPartner(String id, String name, String address) 
      throws PartnerKeyAlreadyExistException {
    
    _warehouse.addPartner(id, name, address);
  }

}
