package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Partner implements Serializable {

    private static final long serialVersionUID = 23761274981643L;

    private String _id;
    private String _name;
    private String _address;
    private double _points;
    private PartnerState _status = new Normal();
    private Collection<Batch> _batches = new ArrayList<>();
    //private List<Notification> _notification;

    private double _valorCompras;           //por simplicidade
    private double _valorVendasEfetuadas;   //por simplicidade
    private double _valorVendasPagas;       //por simplicidade
    /*
    private Set<Acquisition> _acquisition;
    private Set<Sale> _sale;
     // ficam por ordem de criaçao
    // como é que representamos se um Partner tem interesse num produto? 
    // R: Vai ser um padrao de desenho q ainda n demos
    */

    Partner (String id, String name, String address) {
        _id = id;
        _name = name;
        _address = address;
    }

    /**
   * @return the partners's id.
   */
    String getID() {
        return _id;
    }

    /**
     * @return the List of batches.
     */
    Collection<Batch> getBatches() {
        return _batches;
    }

    /**
     * adds a new batch to the batches list
     */
    void addBatch(Batch batch) {
        _batches.add(batch);
    }

    void updateStatus(Date date) {
        //TODO: Update status/points
    }

    /**
   * @return id|nome|endereço|estatuto|pontos|valor-compras|
   *        valor-vendas-efectuadas|valor-vendas-pagas
   *        + notificacoes
   */
    @Override
    public String toString() {
        return  _id + "|" + 
                _name + "|" + 
                _address + "|" + 
                _status.toString() + "|" +
                Math.round(_points) + "|" + 
                Math.round(_valorCompras) + "|" +
                Math.round(_valorVendasEfetuadas) + "|" + 
                Math.round(_valorVendasPagas);
    }
}
