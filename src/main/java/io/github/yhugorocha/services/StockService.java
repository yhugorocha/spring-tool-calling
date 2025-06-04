package io.github.yhugorocha.services;

import io.github.yhugorocha.client.TwelveClient;
import io.github.yhugorocha.dto.DailyStockData;
import io.github.yhugorocha.dto.StockRequest;
import io.github.yhugorocha.dto.StockResponse;
import org.springframework.stereotype.Service;


import java.util.function.Function;

@Service
public class StockService implements Function<StockRequest, StockResponse> {

    private final TwelveClient twelveClient;

    public StockService(TwelveClient twelveClient) {
        this.twelveClient = twelveClient;
    }

    @Override
    public StockResponse apply(StockRequest stockRequest) {
        var stock = twelveClient.getStock(stockRequest.company());
        if(stock.getValues().isEmpty()){
            throw new RuntimeException("Dados de ações não encontrados ou vazios.");
        }

        DailyStockData dailyStockData = stock.getValues().getFirst();
        return new StockResponse(Float.parseFloat(dailyStockData.getClose()));
    }
}
