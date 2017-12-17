package com.neil.oms.service.impl;

import com.neil.oms.model.ISIN;
import com.neil.oms.model.Portfolio;
import com.neil.oms.model.Stock;
import com.neil.oms.service.HardcodedValuesService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class HardcodedValuesServiceImpl implements HardcodedValuesService {

    private Portfolio portfolio;
    private int orderInputCounter;
    private Stock appleStock;
    private Stock vodaStock;

    @PostConstruct
    public void init() {
        portfolio = new Portfolio(1L, new BigDecimal(1000));
        orderInputCounter = 1;

        ISIN appleIsin = new ISIN(1L, "APPL");
        appleStock = new Stock(1L, appleIsin);
        appleStock.setBuyPrice(new BigDecimal("174.04"));
        appleStock.setSellPrice(new BigDecimal("174.14"));

        ISIN vodaIsin = new ISIN(2L, "VOD");
        vodaStock = new Stock(2L, vodaIsin);
        vodaStock.setBuyPrice(new BigDecimal("28.80"));
        vodaStock.setSellPrice(new BigDecimal("31.21"));
    }

    @Override
    public Portfolio getPortfolioForDemo() {
        return portfolio;
    }

    @Override
    public List<Stock> getStocksForDemo() {
        return Arrays.asList(getStockForDemo("APPL"), getStockForDemo("VOD"));
    }

    @Override
    public Stock getStockForDemo(String isin) {
        Stock stock = null;

        switch (isin) {
            case ("APPL"):
                stock = appleStock;
                break;
            case ("VOD"):
                stock = vodaStock;
                break;
        }

        return stock;
    }

    @Override
    public long getNextOrderId() {
        return orderInputCounter++;
    }
}
