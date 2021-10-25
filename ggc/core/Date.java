package ggc.core;

import ggc.core.exception.NegativeDaysException;

/**
 * Class Date implements a date.
 */
public class Date{

    private int _day;

    public int getDay(){
        return this._day;
    }

    /**
   * @param days number of days to increase current Date.
   * @throws ?
   * @return updated Date.
   */
    public Date add(int days) throws NegativeDaysException{
        if (days < 0)
            throw new NegativeDaysException();
        this._day += days;
        return this;
    }

    /**
   * @param other another Date to be compared.
   * @throws ?
   * @return int with the difference of days between Dates.
   */
    public int difference(Date other){
        return this._day - other.getDay();
    }

}
