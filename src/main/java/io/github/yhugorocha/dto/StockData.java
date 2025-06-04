package io.github.yhugorocha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StockData {

    @JsonProperty("values")
    private List<DailyStockData> values;

    public StockData() {
    }

    public StockData(List<DailyStockData> values) {
        this.values = values;
    }

    public List<DailyStockData> getValues() {
        return values;
    }

    public void setValues(List<DailyStockData> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "values=" + values +
                '}';
    }
}
