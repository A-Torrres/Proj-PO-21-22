package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private Collection<Acquisition> _acquisitions = new ArrayList<>();

    private Collection<Sale> _sales = new HashSet<>();

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

    public Collection<Notification> getPartnerNotifications() {
        return Collections.unmodifiableCollection(_notifications);
    }

    void clearNotifications() {
        _notifications.clear();
    }

    public void update(NotificationType type, Product product, double price) {
        _notifications.add(new Notification(type, product, price));
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

    void addAcquisition(Acquisition acquisition) {
        _acquisitions.add(acquisition);
    }

    double getTotalAmountPayed() {
        double total = 0;
        for(Acquisition a: _acquisitions)
            total += a.getBaseValue() * a.getQuantity();
        return total;
    }

    /**
   * @return id|nome|endereço|estatuto|pontos|valor-compras|
   *        valor-vendas-efectuadas|valor-vendas-pagas
   */
    @Override
    public String toString() {
        return  _id + "|" + 
                _name + "|" + 
                _address + "|" + 
                _status.toString() + "|" +
                Math.round(_points) + "|" + 
                Math.round(getTotalAmountPayed()) + "|" +
                Math.round(_valorVendasEfetuadas) + "|" + 
                Math.round(_valorVendasPagas);
    }

}
