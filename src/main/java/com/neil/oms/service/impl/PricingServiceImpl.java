package com.neil.oms.service.impl;

import com.neil.oms.model.Stock;
import com.neil.oms.service.HardcodedValuesService;
import com.neil.oms.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class PricingServiceImpl implements PricingService {

    private HardcodedValuesService hardcodedValuesService;

    @Autowired
    public PricingServiceImpl(HardcodedValuesService hardcodedValuesService) {
        this.hardcodedValuesService = hardcodedValuesService;
    }

    @Override
    public Stock updatePrice(Stock stock) {
        return hardcodedValuesService.getStockForDemo(stock.getIsin().getCode());
    }

    @Override
    public Stock getStock(String isin) {
        return hardcodedValuesService.getStockForDemo(isin);
    }

}
