package com.neil.oms.service;

import com.neil.oms.model.OrderInput;
import com.neil.oms.model.Portfolio;

/**
 * Created by neilmendum on 16/12/2017.
 */
public interface PortfolioService {

    void updatePortfolioForBuyOrder(OrderInput orderInput);

    void updatePortfolioForSellOrder(OrderInput orderInput);

    Portfolio getPortfolio(long id);

}
