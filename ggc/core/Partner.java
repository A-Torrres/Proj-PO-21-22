package ggc.core;

public class Partner {
    
    private String _name;
    private String _address;
    private String _id;
    private String _status;
    private double _points;
    // falta compras e vendas q sao transacoes
    // falta notificacao

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
