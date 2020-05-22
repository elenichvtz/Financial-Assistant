package com.example.finassistant.domain;

import java.util.Date;

public class Exchange{
     private double sum;
     private Date dateEnd;
     private ExchangeCategory exchange = ExchangeCategory.ONLINE; //default

    public Exchange(){
        this.sum = 0;
        this.dateEnd = null;
    }

    public Exchange(double sum, Date dateEnd) {
        this.sum = sum;
        this.dateEnd = dateEnd;
    }

    public double getSum() { return sum; }

    public void setSum(double sum) {
        if(sum > 0) this.sum = sum;
        else System.out.println("Invalid input");
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ExchangeCategory getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeCategory exchange) {
        this.exchange = exchange;
    }
}
