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

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if(_filename.equals("")) 
      throw new MissingFileAssociationException();

    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(_filename))) {
      objectOutputStream.writeObject(_warehouse);
    }
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
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException, IOException {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
      _warehouse = (Warehouse) objectInputStream.readObject();
    }
    catch (FileNotFoundException fnfe) {
      throw new UnavailableFileException(filename);
    }
    _filename = filename;
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (ImportFileException e) {
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
  
  public Collection<Batch> getBatchesByPartner(String id) throws PartnerDoesNotExistException {
    return Collections.unmodifiableCollection(_warehouse.getBatchesByPartner(id));
  }
  
  public Collection<Batch> getBatchesByProduct(String id) throws ProductDoesNotExistException {
    return Collections.unmodifiableCollection(_warehouse.getBatchesByProduct(id));
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
