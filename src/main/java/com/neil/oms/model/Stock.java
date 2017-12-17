package com.neil.oms.model;

import java.math.BigDecimal;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class Stock {

    private final long id;
    private final ISIN isin;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;

    public Stock(long id, ISIN isin) {
        this.id = id;
        this.isin = isin;
    }

    public Stock(long id, ISIN isin, BigDecimal buyPrice, BigDecimal sellPrice) {
        this.id = id;
        this.isin = isin;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public long getId() {
        return id;
    }

    public ISIN getIsin() {
        return isin;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (id != stock.id) return false;
        return isin.equals(stock.isin);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + isin.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", isin=" + isin +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
