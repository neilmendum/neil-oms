package com.neil.oms.service;

import com.neil.oms.model.Portfolio;
import com.neil.oms.model.Stock;

import java.util.List;

/**
 * Created by neilmendum on 16/12/2017.
 */
public interface HardcodedValuesService {

    Portfolio getPortfolioForDemo();

    List<Stock> getStocksForDemo();

    Stock getStockForDemo(String isin);

    long getNextOrderId();

}
