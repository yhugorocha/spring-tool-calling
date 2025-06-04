package io.github.yhugorocha.config;

import io.github.yhugorocha.client.TwelveClient;
import io.github.yhugorocha.dto.DailyStockData;
import io.github.yhugorocha.dto.ShareResponse;
import io.github.yhugorocha.dto.StockRequest;
import io.github.yhugorocha.dto.StockResponse;
import io.github.yhugorocha.repository.ShareRepository;
import io.github.yhugorocha.services.ShareService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class WalletConfig {

    @Bean
    @Description("Number of shares for each company in my portfolio")
    public Supplier<ShareResponse> numberOfShares(ShareRepository shareRepository){
        return new ShareService(shareRepository);
    }

    @Bean
    @Description("Lasted stock price")
    public Function<StockRequest, StockResponse> lastedStockPrice(TwelveClient twelveClient){
        return stockRequest -> {
            var stock = twelveClient.getStock(stockRequest.company());
            if(stock.getValues().isEmpty()){
                throw new RuntimeException("Dados de ações não encontrados ou vazios.");
            }

            DailyStockData dailyStockData = stock.getValues().getFirst();
            return new StockResponse(Float.parseFloat(dailyStockData.getClose()));
        };
    }
}
