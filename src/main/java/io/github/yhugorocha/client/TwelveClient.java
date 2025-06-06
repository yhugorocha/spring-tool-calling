package io.github.yhugorocha.client;

import io.github.yhugorocha.dto.StockData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TwelveClient {

    private final WebClient webClient;

    @Value("${twelve.key}")
    String apiKey;

    public TwelveClient(@Qualifier("twelveWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public StockData getStock(String company){
        return webClient.get()
                .uri("?apikey={apiKey}&interval=1day&symbol={company}&outputsize=1&format=JSON", apiKey, company)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StockData.class)
                .block();
    }

    public StockData getStock(int days, String company){
        return webClient.get()
                .uri("?apikey={apiKey}&interval=1day&symbol={company}&outputsize={days}&format=JSON", apiKey, company, days)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StockData.class)
                .block();
    }

}
