package com.neil.oms.model.dto.adapter;

import com.neil.oms.model.*;
import com.neil.oms.model.dto.OrderFormDto;
import com.neil.oms.service.OrderService;
import com.neil.oms.service.PortfolioService;
import com.neil.oms.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class OrderFormAdapter implements Function<OrderFormDto, OrderInput> {

    private PricingService pricingService;

    private OrderService orderService;

    private PortfolioService portfolioService;

    @Autowired
    public OrderFormAdapter(PricingService pricingService, OrderService orderService,
                            PortfolioService portfolioService) {
        this.pricingService = pricingService;
        this.orderService = orderService;
        this.portfolioService = portfolioService;
    }

    @Override
    public OrderInput apply(OrderFormDto orderFormDto) {
        Portfolio portfolio = portfolioService.getPortfolio(orderFormDto.getPortfolioId());
        Stock stock = pricingService.getStock(orderFormDto.getIsin());
        Side side = Side.valueOf(orderFormDto.getSide());

        int amountInt = orderFormDto.getAmount();
        BigDecimal amount = new BigDecimal(amountInt);

        BigDecimal cost = side.equals(Side.BUY)
                ? stock.getBuyPrice().multiply(amount)
                : stock.getSellPrice().multiply(amount);

        OrderInput input =
                new OrderInputBuilder()
                        .setId(orderService.getNextId())
                        .setPortfolio(portfolio)
                        .setStock(stock)
                        .setAmount(amountInt)
                        .setCost(cost)
                        .setSide(side)
                        .build();
        return input;
    }
}
