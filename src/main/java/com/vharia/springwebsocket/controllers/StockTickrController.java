package com.vharia.springwebsocket.controllers;

import com.vharia.springwebsocket.models.Stock;
import com.vharia.springwebsocket.services.StockPriceFetchService;
import com.vharia.springwebsocket.services.StockWatchlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StockTickrController {

    private final StockWatchlistService stockWatchlistService;
    
    private final StockPriceFetchService stockPriceService;

    @Autowired
    public StockTickrController(StockWatchlistService stockWatchlistService, StockPriceFetchService stockPriceService){
        this.stockWatchlistService = stockWatchlistService;
        this.stockPriceService = stockPriceService;
    }

    @MessageMapping("/stockTickr")
    public ResponseEntity addStock(Stock stock) {
        stockWatchlistService.addStock(stock);
        stockPriceService.addStock(stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}