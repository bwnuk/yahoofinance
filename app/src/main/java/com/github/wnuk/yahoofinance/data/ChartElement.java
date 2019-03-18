package com.github.wnuk.yahoofinance.data;

public class ChartElement {
    private String time;
    private String value;

    public ChartElement(String time, String value) {
        this.time = time;
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }
}
