package com.neil.oms.model;

import java.math.BigDecimal;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class OrderInput {

    private final long id;
    private final Portfolio portfolio;
    private final Stock stock;
    private final int amount;
    private final BigDecimal cost;
    private final Side side;

    protected OrderInput(long id, Portfolio portfolio, Stock stock, int amount, BigDecimal cost, Side side) {
        this.id = id;
        this.portfolio = portfolio;
        this.stock = stock;
        this.amount = amount;
        this.cost = cost;
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Stock getStock() {
        return stock;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInput that = (OrderInput) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (!portfolio.equals(that.portfolio)) return false;
        if (!stock.equals(that.stock)) return false;
        if (!cost.equals(that.cost)) return false;
        return side == that.side;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + portfolio.hashCode();
        result = 31 * result + stock.hashCode();
        result = 31 * result + amount;
        result = 31 * result + cost.hashCode();
        result = 31 * result + side.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderInput{" +
                "id=" + id +
                ", portfolio=" + portfolio +
                ", stock=" + stock +
                ", amount=" + amount +
                ", cost=" + cost +
                ", side=" + side +
                '}';
    }
}
