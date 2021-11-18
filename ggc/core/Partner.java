package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    private Collection<Sale> _sales = new ArrayList<>();


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

    void removeEmptyBatches() {
        _batches.removeIf(b -> b.getQuantity() == 0);
    }

    void addPoints(double points) {
        _points += points;
        updateStatus();
    }

    void updateStatus() {
        if(_status.getPointThreshold() > _points && _status != Normal.getStatus()) {
            _status = _status.getPrevious();
            updateStatus();
        } 
        else if(_status.getNext().getPointThreshold() < _points && _status != Elite.getStatus()) {
            _status = _status.getNext();
            updateStatus();
        }
    }

    void verifyLatePayments(Date currentDate) {
        for(Sale sale: _sales)
            if(sale instanceof SaleByCredit)
                if(-_status.getGracePeriod() > sale.updatePeriod(currentDate) && !sale.isLate()) {
                    _points *= _status.getPointsRemaining();
                    _status = _status.getPrevious();
                    sale.setPaymentAsLate();
                }
    }

    Collection<Sale> getSales() {
        return _sales;
    }

    Collection<Acquisition> getAcquisitions() {
        return _acquisitions;
    }

    Collection<Transaction> getPaidTransactions() {
        List<Transaction> paidTransactions = new ArrayList<>();
        for(Transaction sale: _sales)
            if(sale.isPaid())
                paidTransactions.add(sale);

        return paidTransactions;
    }

    void addAcquisition(Acquisition acquisition) {
        _acquisitions.add(acquisition);
    }

    double getTotalAquisitionsAmountPayed() {
        double total = 0;
        for(Acquisition a: _acquisitions)
            total += a.getBaseValue();
        return total;
    }

    void addSale(Sale sale) {
        _sales.add(sale);
    }

    double getValueOfSales() {
        double total = 0;
        for(Sale sale: _sales)
            if(sale instanceof SaleByCredit)
                total += sale.getBaseValue();
        
        return total;
    }
    
    double getPaidSales() {
        double total = 0;
        for(Sale sale: _sales)
            if(sale instanceof SaleByCredit && sale.isPaid())
                total += sale.getCurrentPrice();
        
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
                Math.round(getTotalAquisitionsAmountPayed()) + "|" +
                Math.round(getValueOfSales()) + "|" + 
                Math.round(getPaidSales());
    }

}
