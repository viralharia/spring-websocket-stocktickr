package com.vharia.springwebsocket.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.vharia.springwebsocket.models.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/**
 * This service fetches the price every 1.5 of the stocks and dumps it in the db (HashMap in our example)
 */
@Service
public class StockPriceFetchService {

    private final Map<String, BigDecimal> stockPricesMap = new HashMap<>();

    private static final MathContext mathContext = new MathContext(2);

    private final Random random = new Random();

    @Autowired
    StockWatchlistService stockWatchlistService;

    public void addStock(Stock stock) {
        BigDecimal randomPrice = generateRandomPrice(stock);
        stockPricesMap.put(stock.getStockSymbol(), randomPrice);
	}

    @Scheduled(fixedRate = 1500)
    private void fetchPrices(){
        if(stockWatchlistService.getStocksWatchlist().isEmpty()) return;

        Set<Stock> stocks = stockWatchlistService.getStocksWatchlist();
        for(Stock stock : stocks){
            BigDecimal randomPrice = generateRandomPrice(stock);
            stockPricesMap.put(stock.getStockSymbol(), randomPrice);
        }
    }

    BigDecimal generateRandomPrice(Stock stock) {
        BigDecimal prevPrice = stockPricesMap.getOrDefault(stock.getStockSymbol(), new BigDecimal((random.nextDouble() * 100),mathContext));
        double range = prevPrice.multiply(new BigDecimal(0.03)).doubleValue();
		BigDecimal priceChange = new BigDecimal((this.random.nextDouble() * range), mathContext);
		return prevPrice.add(priceChange);

    }

    public Map<String, BigDecimal> getStockPricesMap() {
        return stockPricesMap;
    }

}