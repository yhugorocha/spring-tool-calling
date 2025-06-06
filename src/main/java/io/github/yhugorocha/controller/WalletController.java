package io.github.yhugorocha.controller;

import io.github.yhugorocha.tools.ShareTools;
import io.github.yhugorocha.tools.StockTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class WalletController {

    private final ChatClient chatClient;
    private final ShareTools shareTools;
    private final StockTools stockTools;

    public WalletController(ChatClient.Builder chatClient, ShareTools shareTools, StockTools stockTools) {
        this.chatClient = chatClient
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        this.shareTools = shareTools;
        this.stockTools = stockTools;
    }

    //Chamada a tools estáticas (precisa definir as tools)
    @GetMapping("/wallet")
    public String calculateWalletValue(){
        var promptTemplate = new PromptTemplate("""
                What’s the current value in dollars of my wallet based on the latest stock daily prices?
            To improve readability, add tables and line breaks when deemed necessary.
            """);

        return this.chatClient.prompt(promptTemplate.create(
                ToolCallingChatOptions.builder()
                        .toolNames("numberOfShares", "lastedStockPrice")
                        .build()
        )).call().content();
    }

    //Chamada a tools dinâmicas
    @GetMapping("/with-tools")
    public Flux<String> calculateWalletValueWithTools() {
        var promptTemplate = new PromptTemplate("""
                    What’s the current value in dollars of my wallet based on the latest stock daily prices?
                    To improve readability, add tables and line breaks when deemed necessary.
                """);

        return this.chatClient
                .prompt(promptTemplate.create())
                .tools(shareTools, stockTools)
                .stream()
                .content();
    }

    @GetMapping("/with-tools/{days}")
    public Flux<String> calculateWalletValueWithTools(@PathVariable int days) {
        var promptTemplate = new PromptTemplate("""
                   On which day during last {days} days my wallet had the highest value in dollars based on the historical daily stock prices?
                   To improve readability, add tables and line breaks when deemed necessary.
                """);

        promptTemplate.add("days", days);

        return this.chatClient
                .prompt(promptTemplate.create())
                .tools(shareTools, stockTools)
                .stream()
                .content();
    }
}
