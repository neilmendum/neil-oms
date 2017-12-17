package com.neil.oms;

import com.neil.oms.model.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class TestArtifacts {

    public static long getId() {
        return 1L;
    }

    public static Portfolio getPortfolio() {
        Portfolio p = new Portfolio(1L, new BigDecimal("100"));
        return p;
    }

    public static Portfolio getPortfolioWithVodaStockAlready() {
        Portfolio p = getPortfolio();
        Map<ISIN, Integer> purchases = new HashMap<>();
        purchases.put(getVodaStock().getIsin(), 10);
        p.setInventory(purchases);
        return p;
    }

    public static Stock getVodaStock() {
        Stock s = new Stock(1L, getVodaIsin());
        s.setSellPrice(BigDecimal.TEN);
        s.setBuyPrice(BigDecimal.TEN);
        return s;
    }

    public static ISIN getVodaIsin() {
        return new ISIN(1L, "VOD");
    }

    public static BigDecimal calcCost(BigDecimal price, int amount) {
        return price.multiply(BigDecimal.valueOf(amount));
    }

    public static OrderInput getOrderInput(Portfolio portfolio, int amount, Side side) {
        Stock vodaStock = getVodaStock();
        OrderInput orderInput =
                new OrderInputBuilder()
                        .setId(getId())
                        .setPortfolio(portfolio)
                        .setStock(vodaStock)
                        .setAmount(amount)
                        .setCost(calcCost(vodaStock.getBuyPrice(), amount))
                        .setSide(side)
                        .build();
        return orderInput;
    }
}
