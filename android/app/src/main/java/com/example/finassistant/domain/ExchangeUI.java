package com.example.finassistant.domain;

import java.util.Date;

public interface ExchangeUI {

    double getSum();

    void setSum(double sum);

    Date getDateEnd();

    void setDateEnd(Date dateEnd);

    ExchangeCategory getExchange();

    void setExchange(ExchangeCategory exchange);
}
