package com.neil.oms.service.impl;

import com.neil.oms.model.ISIN;
import com.neil.oms.model.OrderInput;
import com.neil.oms.model.Portfolio;
import com.neil.oms.model.Stock;
import com.neil.oms.service.HardcodedValuesService;
import com.neil.oms.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class PortfolioServiceImpl implements PortfolioService {

    private HardcodedValuesService hardcodedValuesService;

    @Autowired
    public PortfolioServiceImpl(HardcodedValuesService hardcodedValuesService) {
        this.hardcodedValuesService = hardcodedValuesService;
    }

    @Override
    public void updatePortfolioForBuyOrder(OrderInput orderInput) {
        Portfolio portfolio = orderInput.getPortfolio();
        Map<ISIN, Integer> inventory = portfolio.getInventory();
        Stock stock = orderInput.getStock();
        int amount = orderInput.getAmount();

        Integer existingAmount = inventory.get(stock.getIsin());
        if (null != existingAmount) {
            amount += existingAmount;
        }

        inventory.put(stock.getIsin(), amount);
        portfolio.setInventory(inventory);

        BigDecimal newBalance = portfolio.getBalance().subtract(orderInput.getCost());
        portfolio.setBalance(newBalance);
    }

    @Override
    public void updatePortfolioForSellOrder(OrderInput orderInput) {
        Portfolio portfolio = orderInput.getPortfolio();
        Map<ISIN, Integer> inventory = portfolio.getInventory();
        Stock stock = orderInput.getStock();

        BigDecimal newBalance = portfolio.getBalance().add(orderInput.getCost());
        portfolio.setBalance(newBalance);

        int amount = orderInput.getAmount();
        Integer existingAmount = portfolio.getInventory().get(stock.getIsin());
        amount = existingAmount - amount;

        if (amount == 0) {
            inventory.remove(stock.getIsin());
            portfolio.setInventory(inventory);
            return;
        }

        inventory.put(stock.getIsin(), amount);
        portfolio.setInventory(inventory);
    }

    @Override
    public Portfolio getPortfolio(long id) {
        return hardcodedValuesService.getPortfolioForDemo();
    }
}
