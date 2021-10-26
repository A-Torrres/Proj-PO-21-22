package ggc.core;

import java.io.Serializable;

import ggc.core.exception.NegativeDaysException;

/**
 * Class Date implements a date.
 */
public class Date implements Serializable {

    private static final long serialVersionUID = 201589871010L; //?!?!
    private int _day;

    int getDay(){
        return this._day;
    }

    /**
   * @param days number of days to increase current Date.
   * @throws NegativeDaysException
   * @return updated Date.
   */
    Date add(int days) throws NegativeDaysException{
        if (days < 0)
            throw new NegativeDaysException();
        this._day += days;
        return this;
    }

    /**
   * @param other another Date to be compared.
   * @return int with difference of days between current Date and other Date.
   */
    int difference(Date other){
        return this._day - other.getDay();
    }

}
