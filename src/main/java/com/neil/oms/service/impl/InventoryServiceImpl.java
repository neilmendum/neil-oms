package com.neil.oms.service.impl;

import com.neil.oms.model.Stock;
import com.neil.oms.service.HardcodedValuesService;
import com.neil.oms.service.InventoryService;
import com.neil.oms.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Component
public class InventoryServiceImpl implements InventoryService {

    private HardcodedValuesService hardcodedValuesService;

    @Autowired
    public InventoryServiceImpl(HardcodedValuesService hardcodedValuesService) {
        this.hardcodedValuesService = hardcodedValuesService;
    }

    @Override
    public List<Stock> getInventory() {
        return hardcodedValuesService.getStocksForDemo();
    }
}
