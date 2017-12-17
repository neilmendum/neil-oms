package com.neil.oms.model.dto;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class OrderFormDto {

    private long portfolioId;
    private String isin;
    private int amount;
    private String side;

    public OrderFormDto() {
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
