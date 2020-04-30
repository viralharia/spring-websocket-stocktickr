package com.vharia.springwebsocket.services;
import java.util.stream.Collectors;
import com.vharia.springwebsocket.models.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/**
 * This service is used to send Stock prices to every subscriber every 2 secs
 */
@Service
public class StockTickrService implements ApplicationListener<BrokerAvailabilityEvent> {
    
    private final StockWatchlistService stockWatchlistService;
    
    private final StockPriceFetchService stockPriceService;
   
    private final SimpMessagingTemplate template;

    private Boolean isBrokerAvailable;

    @Autowired
    public StockTickrService(StockWatchlistService stockWatchlistService, StockPriceFetchService stockPriceService, SimpMessagingTemplate template){
        this.stockWatchlistService = stockWatchlistService;
        this.stockPriceService = stockPriceService;
        this.template = template;
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        this.isBrokerAvailable = event.isBrokerAvailable();
    }

    @Scheduled(fixedRate = 2000)
    public void sendStockPrices() {
       
        if(stockWatchlistService.getStocksWatchlist().isEmpty() || !isBrokerAvailable) return;

        template.convertAndSend("/stockTickr/price", stockWatchlistService.getStocksWatchlist()
                                                        .stream()
                                                        .map(stock -> new StockQuote(stock.getStockSymbol(), stockPriceService.getStockPricesMap().get(stock.getStockSymbol())))
                                                        .collect(Collectors.toList()));

        //System.out.println("sending stock prices");
    }

}