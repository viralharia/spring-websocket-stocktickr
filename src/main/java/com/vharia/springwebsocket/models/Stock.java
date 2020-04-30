package com.vharia.springwebsocket.models;

public class Stock {

    private String stockSymbol;

    public Stock() {
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        if (stockSymbol == null) {
            if (other.stockSymbol != null)
                return false;
        } else if (!stockSymbol.equalsIgnoreCase(other.stockSymbol))
            return false;
        return true;
    }

    

    

}