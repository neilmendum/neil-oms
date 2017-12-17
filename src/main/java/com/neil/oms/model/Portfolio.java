package com.neil.oms.model;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class Portfolio {

    private final long id;
    private BigDecimal balance;
    private Map<ISIN, Integer> inventory;

    public Portfolio(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
        this.inventory = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Map<ISIN, Integer> getInventory() {
        return inventory.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    public void setInventory(Map<ISIN, Integer> inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;

        return id == portfolio.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", balance=" + balance +
                ", inventory=" + inventory +
                '}';
    }
}
