package com.vharia.springwebsocket.models;

import java.math.BigDecimal;
import java.util.Date;


public class StockQuote {

    public String stockCode;
    public BigDecimal stockPrice;
    public Date date;

    public StockQuote(String stockCode, BigDecimal stockPrice) {
        this.stockCode = stockCode;
        this.stockPrice = stockPrice;
        date = new Date();
    }

    public String getStockCode() {
        return stockCode;
    }

    
    public BigDecimal getStockPrice() {
        return stockPrice;
    }    

    public Date getDate() {
        return date;
    }
    

}