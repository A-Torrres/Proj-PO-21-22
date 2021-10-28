package ggc.core;

import java.util.List;
import java.util.Set;

public class Partner {
    
    private String _name;
    private String _address;
    private String _id; //como é gerado o id(random number gen, incrementador estatico, hashcode, etc...)?
    private String _status = "Normal"; //implementar enum class
    private double _points = 0;
    private Set<Acquisition> _acquisition;
    private Set<Sale> _sale;
    private List<Notification> _notification; // ficam por ordem de criaçao
    // como é que representamos se um Partner tem interesse num produto? 
    // R: Vai ser um padrao de desenho q ainda n demos

    public Partner(String name, String add) {
        _name = name;
        _address = add;
        _id = "" + name.hashCode(); //temp solution
    }

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
   *        tipo-de-notificação|idProduto|preço-do-produto
   */
    @Override
    public String toString() {
        return  "" + _id + "|" + _name + "|" + _status + "|" + _points + "|" + _acquisition + "|" + _sale; //Fazer um metodo para _sale que faz a differença entre pagos/não-pagos
    }
}
