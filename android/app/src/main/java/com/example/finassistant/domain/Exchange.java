package com.example.finassistant.domain;

import java.util.Date;

/**
 * The type Exchange.
 */
public class Exchange{
     private double sum;
     private Date dateEnd;
     private ExchangeCategory exchange = ExchangeCategory.ONLINE; //default

    /**
     * Instantiates a new Exchange.
     */
    public Exchange(){
        this.sum = 0;
        this.dateEnd = null;
    }

    /**
     * Instantiates a new Exchange.
     *
     * @param sum     the sum
     * @param dateEnd the date end
     */
    public Exchange(double sum, Date dateEnd) {
        this.sum = sum;
        this.dateEnd = dateEnd;
    }

    /**
     * Gets sum.
     *
     * @return the sum
     */
    public double getSum() { return sum; }

    /**
     * Sets sum.
     *
     * @param sum the sum
     */
    public void setSum(double sum) {
        if(sum > 0) this.sum = sum;
        else System.out.println("Invalid input");
    }

    /**
     * Gets date end.
     *
     * @return the date end
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets date end.
     *
     * @param dateEnd the date end
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Gets exchange.
     *
     * @return the exchange
     */
    public ExchangeCategory getExchange() {
        return exchange;
    }

    /**
     * Sets exchange.
     *
     * @param exchange the exchange
     */
    public void setExchange(ExchangeCategory exchange) {
        this.exchange = exchange;
    }
}
