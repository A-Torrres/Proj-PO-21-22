package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.text.Normalizer.Form;
import java.util.Collection;
import java.util.Collections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;
import ggc.core.exception.NegativeDaysException;
import ggc.core.exception.PartnerDoesNotExistException;
import ggc.core.exception.PartnerKeyAlreadyExistException;
import ggc.core.exception.ProductDoesNotExistException;

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
    /*
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(_filename + ".bin"));

    objectOutputStream.writeObject(_warehouse);
    */
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
   * @throws IOException
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {
    /*
    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(_filename + ".bin"));

    _warehouse = (Warehouse) objectInputStream.readObject();
    objectInputStream.close();
    */
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException | ProductDoesNotExistException | PartnerDoesNotExistException | PartnerKeyAlreadyExistException  e) {
      throw new ImportFileException(textfile, e);
    }
  }

  public String getFileName() {
    return _filename;
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
    return Collections.unmodifiableCollection(_warehouse.getProducts());
  }

  /**
   * 
   */
  public Collection<Batch> getBatches() {
    return Collections.unmodifiableCollection(_warehouse.getBatches());
  }

  public Partner getPartner(String id) throws PartnerDoesNotExistException {
    return _warehouse.getPartner(id);
  }

  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_warehouse.getPartners());
  }

  public void addPartner(String id, String name, String address) 
      throws PartnerKeyAlreadyExistException {
    
    _warehouse.addPartner(id, name, address);
  }

}
