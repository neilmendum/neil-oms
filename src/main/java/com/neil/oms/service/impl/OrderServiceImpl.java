package com.neil.oms.service.impl;

import com.neil.oms.model.*;
import com.neil.oms.service.HardcodedValuesService;
import com.neil.oms.service.OrderService;
import com.neil.oms.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private PortfolioService portfolioService;

    private HardcodedValuesService hardcodedValuesService;

    @Autowired
    public OrderServiceImpl(PortfolioService portfolioService, HardcodedValuesService hardcodedValuesService) {
        this.portfolioService = portfolioService;
        this.hardcodedValuesService = hardcodedValuesService;
    }

    @Override
    public OrderStatus placeOrder(OrderInput orderInput) {
        OrderStatus orderStatus = null;

        if (orderInput.getAmount() <= 0) {
            log.debug("Invalid order ID: {}, amount: {}", orderInput.getId(), orderInput.getAmount());
            return new OrderStatus(orderInput.getId(), orderInput.getStock(), OrderStatusMessage.INVALID_NEGATIVE);
        }

        switch (orderInput.getSide()) {
            case BUY:
                orderStatus = validateBuyOrder(orderInput);
                break;
            case SELL:
                orderStatus = validateSellOrder(orderInput);
                break;
        }

        return orderStatus;
    }

    public long getNextId() {
        return hardcodedValuesService.getNextOrderId();
    }

    private OrderStatus validateBuyOrder(OrderInput orderInput) {
        // Unpack
        Stock stock = orderInput.getStock();
        Portfolio portfolio = orderInput.getPortfolio();

        BigDecimal portfolioBalanceMinusOrder = portfolio.getBalance().subtract(orderInput.getCost());

        if (portfolioCanBuy(portfolioBalanceMinusOrder)) {
            portfolioService.updatePortfolioForBuyOrder(orderInput);
            return new OrderStatus(orderInput.getId(), stock, OrderStatusMessage.SUCCESS);
        } else {
            return new OrderStatus(orderInput.getId(), stock, OrderStatusMessage.INVALID_BUY);
        }
    }

    private OrderStatus validateSellOrder(OrderInput orderInput) {
        // Unpack
        Stock stock = orderInput.getStock();
        Portfolio portfolio = orderInput.getPortfolio();

        Integer existingAmount = portfolio.getInventory().get(stock.getIsin());
        if (null == existingAmount)
            return new OrderStatus(orderInput.getId(), stock, OrderStatusMessage.INVALID_SELL);

        if (portfolioCanSell(existingAmount, orderInput.getAmount())) {
            portfolioService.updatePortfolioForSellOrder(orderInput);
            return new OrderStatus(orderInput.getId(), stock, OrderStatusMessage.SUCCESS);
        } else {
            return new OrderStatus(orderInput.getId(), stock, OrderStatusMessage.INVALID_SELL_NEGATIVE);
        }
    }

    private boolean portfolioCanBuy(BigDecimal newPortfolioBalance) {
        return newPortfolioBalance.compareTo(BigDecimal.ZERO) > -1;
    }

    private boolean portfolioCanSell(int existingAmount, int amountToSell) {
        return existingAmount >= amountToSell;
    }

}
