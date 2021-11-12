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

    public Collection<Notification> getPartnerNotifications() {
        return Collections.unmodifiableCollection(_notifications);
    }

    void clearNotifications() {
        _notifications.clear();
    }

    public void update(NotificationType type, Product product, double price) {
        _notifications.add(new Notification(type, product, price));
    }

    /**
     * adds a new batch to the batches list
     */
    void addBatch(Batch batch) {
        _batches.add(batch);
    }

    void verifyPaymentPeriod(Date date) {
        for(Sale sale : _sales)
            if(sale instanceof SaleByCredit)
                if(-_status.getGracePeriod() > sale.updatePeriod(date)) {
                    _points *= _status.getPointsRemaining();
                    _status = _status.getPrevious();
                }
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
