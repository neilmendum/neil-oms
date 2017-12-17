package com.neil.oms.model;

import java.math.BigDecimal;

public class OrderInputBuilder {

    private long id;
    private Portfolio portfolio;
    private Stock stock;
    private int amount;
    private BigDecimal cost;
    private Side side;

    public OrderInputBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public OrderInputBuilder setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        return this;
    }

    public OrderInputBuilder setStock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public OrderInputBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public OrderInputBuilder setCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public OrderInputBuilder setSide(Side side) {
        this.side = side;
        return this;
    }

    public OrderInput build() {
        return new OrderInput(id, portfolio, stock, amount, cost, side);
    }
}