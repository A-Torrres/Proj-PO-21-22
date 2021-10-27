package ggc.core;

import java.util.List;
import java.util.Set;

public class Partner {
    
    private String _name;
    private String _address;
    private String _id;
    private String _status;
    private double _points;
    private Set<Acquisition> _acquisition;
    private Set<Sale> _sale;
    private List<Notification> _notification; // ficam por ordem de criaçao
    // como é que representamos se um Partner tem interesse num produto? 
    // R: Vai ser um padrao de desenho q ainda n demos

    /**
   * @return the partners's id.
   */
    String getID(){
        return _id;
    }

    /**
   * @return id|nome|endereço|estatuto|pontos|valor-compras|
   *        valor-vendas-efectuadas|valor-vendas-pagas
   *        +
   *        notificacoes
   */
    @Override
    public String toString() {
        return  "" + "|" ;
    }

}
