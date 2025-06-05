package io.github.yhugorocha.tools;

import io.github.yhugorocha.client.TwelveClient;
import io.github.yhugorocha.dto.DailyStockData;
import io.github.yhugorocha.dto.StockResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class StockTools {

    private final TwelveClient twelveClient;

    public StockTools(TwelveClient twelveClient) {
        this.twelveClient = twelveClient;
    }

    @Tool(description = "Lasted stock prices")
    public StockResponse apply(@ToolParam(description = "Company name") String company) {
        var stock = twelveClient.getStock(company);
        if(stock.getValues().isEmpty()){
            throw new RuntimeException("Dados de ações não encontrados ou vazios.");
        }

        DailyStockData dailyStockData = stock.getValues().getFirst();
        return new StockResponse(Float.parseFloat(dailyStockData.getClose()));
    }
}
