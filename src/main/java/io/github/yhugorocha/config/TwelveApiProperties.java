package io.github.yhugorocha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "twelve")
public class TwelveApiProperties {

    private String url;

    public TwelveApiProperties() {
    }

    public TwelveApiProperties(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

