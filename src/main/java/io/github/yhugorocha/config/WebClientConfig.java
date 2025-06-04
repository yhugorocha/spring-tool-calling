package io.github.yhugorocha.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "twelveWebClient")
    public WebClient twelveWebClient(TwelveApiProperties props) {
        return WebClient.builder()
                .baseUrl(props.getUrl())
                .build();
    }
}
