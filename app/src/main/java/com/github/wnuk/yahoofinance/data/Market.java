package com.github.wnuk.yahoofinance.data;

import androidx.annotation.NonNull;

/**
 * Finance institution
 */
public class Market {
    private String symbol;
    private String name;

    public Market(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return symbol + " " + name;
    }
}
