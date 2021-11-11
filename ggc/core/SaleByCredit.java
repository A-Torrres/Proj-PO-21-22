
package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadLine;
    private double _amountPaid;

    SaleByCredit(int id, Date paymentD, double baseValue, int quant, Product prod, Partner part) {
        super(id, paymentD, baseValue, quant, prod, part);
        _deadLine = new Date(paymentD.getDay() + prod.getDeadLine());
    }

    /**
   * @return ?
   */
  @Override
  public String toString() {
      return "";
  }  

}
