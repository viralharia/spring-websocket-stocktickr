package com.vharia.springwebsocket.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vharia.springwebsocket.models.Stock;

import org.springframework.stereotype.Service;

@Service
public class StockWatchlistService {

    private final Set<Stock> stocksWatchlist = new HashSet<>();

	public void addStock(Stock stock) {
        stocksWatchlist.add(stock);
	}

    public Set<Stock> getStocksWatchlist() {
        return stocksWatchlist;
    }
}