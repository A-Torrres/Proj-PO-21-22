package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Partner implements Observer, Serializable {

    private static final long serialVersionUID = 23761274981643L;

    private String _id;
    private String _name;
    private String _address;
    private double _points;
    private PartnerState _status = Normal.getStatus();
    private Collection<Batch> _batches = new ArrayList<>();
    private Collection<Notification> _notifications = new ArrayList<>();

    private Collection<Acquisition> _acquisitions = new HashSet<>();
    private Collection<Sale> _sales = new HashSet<>();

    private double _valorCompras;           //por simplicidade
    private double _valorVendasEfetuadas;   //por simplicidade
    private double _valorVendasPagas;       //por simplicidade


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

    public void update() {
        // TO DO
    }

    PartnerState getStatus() {
        return _status;
    }

    /**
     * adds a new batch to the batches list
     */
    void addBatch(Batch batch) {
        _batches.add(batch);
    }

    void addPoints(double points) {
        _points += points;
        updateStatus();
    }

    void updateStatus() {
        if(_status.getPointThreshold() > _points) {
            _status.getPrevious();
            updateStatus();
        } else if(_status.getNext().getPointThreshold() < _points) {
            _status.getNext();
            updateStatus();
        }
    }

    void verifyLatePayments(Date date) {
        for(Sale sale : _sales) {
            if(sale instanceof SaleByCredit) {
                if(-_status.getGracePeriod() > sale.updatePeriod(date)) {
                    _points *= _status.getPointsRemaining();
                    _status = _status.getPrevious();
                }
            }
        }
    }

    public ArrayList<Sale> getSales() {
        return (ArrayList<Sale>) _sales;
    }

    public ArrayList<Acquisition> getAcquisitions() {
        return (ArrayList<Acquisition>) _acquisitions;
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
