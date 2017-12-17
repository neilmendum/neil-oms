package com.neil.oms.service;

import com.neil.oms.model.Stock;

import java.util.List;

/**
 * Created by neilmendum on 16/12/2017.
 */
public interface InventoryService {

    List<Stock> getInventory();
}
