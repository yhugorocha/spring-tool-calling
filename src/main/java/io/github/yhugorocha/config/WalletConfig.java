package io.github.yhugorocha.config;

import io.github.yhugorocha.client.TwelveClient;
import io.github.yhugorocha.dto.ShareResponse;
import io.github.yhugorocha.dto.StockRequest;
import io.github.yhugorocha.dto.StockResponse;
import io.github.yhugorocha.repository.ShareRepository;
import io.github.yhugorocha.services.ShareService;
import io.github.yhugorocha.services.StockService;
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
        return new StockService(twelveClient);
    }
}
