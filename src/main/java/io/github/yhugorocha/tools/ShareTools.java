package io.github.yhugorocha.tools;

import io.github.yhugorocha.dto.ShareResponse;
import io.github.yhugorocha.repository.ShareRepository;
import org.springframework.ai.tool.annotation.Tool;

public class ShareTools {

    private final ShareRepository stockRepository;

    public ShareTools(ShareRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Tool(description = "Number of shares for each company in my portfolio")
    public ShareResponse getStocks() {
        return new ShareResponse(stockRepository.findAll());
    }
}
