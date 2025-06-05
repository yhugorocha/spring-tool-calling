package io.github.yhugorocha.tools;

import io.github.yhugorocha.client.TwelveClient;
import io.github.yhugorocha.dto.DailyShareQuote;
import io.github.yhugorocha.dto.DailyStockData;
import io.github.yhugorocha.dto.StockResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

public class StockTools {

    private final TwelveClient twelveClient;

    public StockTools(TwelveClient twelveClient) {
        this.twelveClient = twelveClient;
    }

    @Tool(description = "Lasted stock prices")
    public StockResponse getLatestStockPrices(@ToolParam(description = "Company name") String company) {
        var stock = twelveClient.getStock(company);
        if(stock.getValues().isEmpty()){
            throw new RuntimeException("Dados de ações não encontrados ou vazios.");
        }

        DailyStockData dailyStockData = stock.getValues().getFirst();
        return new StockResponse(Float.parseFloat(dailyStockData.getClose()));
    }

    @Tool(description = "Historical daily stock prices")
    public List<DailyShareQuote> getHistoricalStockPrices(@ToolParam(description = "Search period in days") int days ,
                                       @ToolParam(description = "Name of Company") String company) {
        var stock = twelveClient.getStock(days, company);
        if(stock.getValues().isEmpty()){
            throw new RuntimeException("Dados de ações não encontrados ou vazios.");
        }

        return stock.getValues().stream()
                .map(s -> new DailyShareQuote(company, Float.parseFloat(s.getClose()), s.getDatetime()))
                .toList();
    }
}
